package com.lzw.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzw.pojo.User;
import com.lzw.service.UserService;
import com.lzw.service.impl.UserServiceImpl;
import com.lzw.util.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService = new UserServiceImpl();

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(name = "remember", required = false) String remember,
            HttpServletResponse response,
            HttpSession session) throws IOException {

        User user = userService.login(username, password);
        if (user != null) {
            if ("1".equals(remember)) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(604800); // 一周
                c_password.setMaxAge(604800);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            session.setAttribute("user", user);
            return "redirect:/brand.html";
        } else {
            session.setAttribute("error", "用户名或密码错误");
            return "redirect:/login.jsp";
        }
    }


    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("checkCode") String checkCode,
            HttpSession session) {

        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            session.setAttribute("register_msg", "验证码错误");
            return "redirect:/register.jsp";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean flag = userService.register(user);
        if (flag) {
            session.setAttribute("register_msg", "注册成功,请登陆");
            return "redirect:/login.jsp";
        } else {
            session.setAttribute("register_msg", "用户名已存在,请重新注册");
            return "redirect:/register.jsp";
        }
    }

    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCodeGen = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        session.setAttribute("checkCodeGen", checkCodeGen);
    }

    @GetMapping("/selectUser")
    @ResponseBody
    public boolean selectUser(@RequestParam("username") String username) {
        return userService.selectByName(username);
    }

    @PostMapping("/updatePassword")
    public String updatePassword(
            @RequestBody JSONObject jsonObject,
            HttpSession session) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        userService.updatePassword(username, password);
        session.setAttribute("login_msg", "密码已修改请重新登录");
        return "redirect:/login.jsp";
    }

    @PostMapping("/updateName")
    @ResponseBody
    public String updateName(
            @RequestBody JSONObject jsonObject,
            HttpSession session) {

        String username = jsonObject.getString("username");
        String updateName = jsonObject.getString("updateName");

        boolean exists = userService.selectByName(updateName);
        if (exists) {
            return "用户名已存在";
        } else {
            userService.updateName(username, updateName);
            User user = (User) session.getAttribute("user");
            if (user != null) {
                user.setUsername(updateName);
                session.setAttribute("user", user);
            }
            return "success";
        }
    }
}
