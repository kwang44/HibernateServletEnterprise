package com.WangWick.servlet;

import com.WangWick.controller.FrontController;
import com.WangWick.handler.Handler;
import com.WangWick.handler.PostHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    Handler handler;
    FrontController controller;
    public LoginServlet(Handler handler) {
        this.handler = handler;
    }
    public LoginServlet(){
        handler = new PostHandler();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        controller = handler.route(req,resp);
        if(controller != null){
            controller.handle(req,resp);
        }
    }
}
