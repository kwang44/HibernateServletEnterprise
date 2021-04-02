package com.WangWick.servlet;

import com.WangWick.controller.FrontController;
import com.WangWick.controller.Handler;
import com.WangWick.controller.LoginController;
import com.WangWick.service.UserService;

import javax.servlet.ServletException;
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
        handler = new Handler();
    }

    public void init() throws ServletException {


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        controller = handler.route(req,resp);
        if(controller != null){
            controller.handle(req,resp);
        }
    }
}
