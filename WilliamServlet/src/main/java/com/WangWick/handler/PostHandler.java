package com.WangWick.handler;

import com.WangWick.controller.FrontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostHandler extends Handler {
    @Override
    public FrontController route(HttpServletRequest req, HttpServletResponse res) {

            switch (req.getRequestURI()){
                case "/W3/login":
                    return loginController;
                case "/W3/users":
                    return userController;
            }
            return null;
        }

}
