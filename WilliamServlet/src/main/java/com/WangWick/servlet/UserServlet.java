package com.WangWick.servlet;

import com.WangWick.controller.FrontController;
import com.WangWick.handler.GetHandler;
import com.WangWick.handler.Handler;
import com.WangWick.handler.PostHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/users", "/users/**"})
public class UserServlet extends HttpServlet {
    Handler handler;
    FrontController controller;
    public UserServlet(){
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        handler = new PostHandler();
        handler.route(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        handler = new GetHandler();
        handler.route(req,resp);
    }
}
