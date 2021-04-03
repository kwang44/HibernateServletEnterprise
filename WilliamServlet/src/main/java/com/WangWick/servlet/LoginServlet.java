package com.WangWick.servlet;

import com.WangWick.handler.Handler;
import com.WangWick.handler.PostHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    Handler handler;

    public LoginServlet(){
                handler = new PostHandler();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        handler.route(req,resp);

    }
}
