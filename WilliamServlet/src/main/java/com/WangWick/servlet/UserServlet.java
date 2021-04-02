package com.WangWick.servlet;

import com.WangWick.controller.FrontController;
import com.WangWick.handler.Handler;
import com.WangWick.handler.PostHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "users")
public class UserServlet extends HttpServlet {
    Handler handler;
    FrontController controller;
    public UserServlet(){
        handler = new PostHandler();
    }



}
