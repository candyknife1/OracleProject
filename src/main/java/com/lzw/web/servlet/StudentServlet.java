package com.lzw.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzw.pojo.PageBean;
import com.lzw.pojo.Student;
import com.lzw.pojo.User;
import com.lzw.service.StudentService;
import com.lzw.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet({"/student/*"})
public class StudentServlet extends BaseServlet {
    private StudentService studentService = new StudentServiceImpl();

    public StudentServlet() {
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = this.studentService.selectAll();
        String jsonString = JSON.toJSONString(students);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String studentStr = br.readLine();
        Student student = (Student)JSON.parseObject(studentStr, Student.class);
        this.studentService.add(student);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ints = (int[])JSON.parseObject(params, int[].class);
        this.studentService.deleteByIds(ints);
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        User user = (User)request.getSession().getAttribute("user");
        String userName = user.getUsername();
        PageBean<Student> pageBean = this.studentService.selectByPage(currentPage, pageSize);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("pageBean", pageBean);
        jsonResponse.put("userName", userName);
        String jsonString = jsonResponse.toJSONString();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Student student = (Student)JSON.parseObject(params, Student.class);
        PageBean<Student> pageBean = this.studentService.selectByPageAndCondition(currentPage, pageSize, student);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        this.studentService.deleteById(id);
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String brandStr = br.readLine();
        Student student = (Student)JSON.parseObject(brandStr, Student.class);
        this.studentService.update(student);
        response.getWriter().write("success");
    }

    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.setAttribute("error", "登录已过期请重新登录");
        request.getRequestDispatcher("../login.jsp").forward(request, response);
    }
}
