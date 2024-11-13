package com.lzw.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzw.pojo.PageBean;
import com.lzw.pojo.Student;
import com.lzw.pojo.User;
import com.lzw.service.StudentService;
import com.lzw.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService = new StudentServiceImpl();

    @GetMapping("/selectAll")
    @ResponseBody
    public List<Student> selectAll() {
        return studentService.selectAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody Student student) {
        studentService.add(student);
        return "success";
    }

    @PostMapping("/deleteByIds")
    @ResponseBody
    public String deleteByIds(@RequestBody int[] ids) {
        studentService.deleteByIds(ids);
        return "success";
    }

    @GetMapping("/selectByPage")
    @ResponseBody
    public JSONObject selectByPage(
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize,
            HttpSession session) {
        PageBean<Student> pageBean = studentService.selectByPage(currentPage, pageSize);
        String userName = ((User) session.getAttribute("user")).getUsername();
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("pageBean", pageBean);
        jsonResponse.put("userName", userName);
        return jsonResponse;
    }

    @PostMapping("/selectByPageAndCondition")
    @ResponseBody
    public PageBean<Student> selectByPageAndCondition(
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize,
            @RequestBody Student student) {
        return studentService.selectByPageAndCondition(currentPage, pageSize, student);
    }

    @GetMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") int id) {
        studentService.deleteById(id);
        return "success";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestBody Student student) {
        studentService.update(student);
        return "success";
    }

    @PostMapping("/out")
    public String quit(HttpSession session) {
        session.invalidate();
        return "redirect:../login.jsp";
    }
}
