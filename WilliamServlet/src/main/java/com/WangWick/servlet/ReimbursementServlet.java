package com.WangWick.servlet;

import com.WangWick.handler.GetHandler;
import com.WangWick.handler.Handler;
import com.WangWick.handler.PostHandler;
import com.WangWick.handler.PutHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/users/reimbursements","/user/{id}/reimbursement"})
public class ReimbursementServlet extends HttpServlet {
    Handler handler;
    public ReimbursementServlet(){
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        handler = new PutHandler();
        handler.route(req,resp);
    }
}
