
package com.lzw.web.servlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzw.pojo.User;
import com.lzw.service.UserService;
import com.lzw.service.impl.UserServiceImpl;
import com.lzw.util.CheckCodeUtil;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet({"/user/*"})
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public UserServlet() {
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = this.userService.login(username, password);
        if (user != null) {
            if ("1".equals(remember)) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_password.setMaxAge(604800);
                c_username.setMaxAge(604800);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/brand.html");
        } else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("../login.jsp").forward(request, response);
        }

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCodeGen");
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            request.setAttribute("register_msg", "验证码错误");
            request.getRequestDispatcher("../register.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            boolean flag = this.userService.register(user);
            if (flag) {
                request.setAttribute("register_msg", "注册成功,请登陆");
                request.getRequestDispatcher("../login.jsp").forward(request, response);
            } else {
                request.setAttribute("register_msg", "用户名已存在,请重新注册");
                request.getRequestDispatcher("../register.jsp").forward(request, response);
            }

        }
    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCodeGen = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen", checkCodeGen);
    }

    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean flag = this.userService.selectByName(username);
        response.getWriter().write("" + flag);
    }

    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String jsonStr = br.readLine();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        this.userService.updatePassword(username, password);
        request.setAttribute("login_msg", "密码已修改请重新登录");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void updateName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String jsonStr = br.readLine();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String username = jsonObject.getString("username");
        String updateName = jsonObject.getString("updateName");
        boolean flag = this.userService.selectByName(updateName);
        if (!flag) {
            response.getWriter().write("true");
        } else {
            this.userService.updateName(username, updateName);
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            user.setUsername(updateName);
            session.setAttribute("user", user);
            response.getWriter().write("success");
        }

    }
}
