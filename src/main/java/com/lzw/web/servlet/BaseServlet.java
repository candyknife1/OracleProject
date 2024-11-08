package com.lzw.web.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 功能类似于 BaseServlet 的方法分发功能
 * 通过请求路径的最后一段进行方法分发
 */
@WebServlet("/*") // 拦截所有请求
public class BaseServlet extends HttpServlet {

    //根据请求路径的最后一段来分发方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求路径
        String uri = req.getRequestURI(); // 例如：/MyApp/user/addUser
        // System.out.println("Request URI: " + uri);

        // 2. 获取请求路径的最后一部分作为方法名
        int lastSlashIndex = uri.lastIndexOf("/");
        String methodName = uri.substring(lastSlashIndex + 1); // 获取方法名，忽略前面的路径
        // System.out.println("Method to be invoked: " + methodName);

        // 3. 使用反射调用对应的方法
        Class<? extends BaseServlet> clazz = this.getClass(); // 获取当前类的 Class 对象
        try {
            // 3.1 获取方法对象
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 3.2 调用方法
            method.invoke(this, req, resp);

        } catch (NoSuchMethodException e) {
            // 如果方法不存在，返回 404 错误
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested method does not exist.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred.");
        }
    }
}
