package com.WangWick.controller;

import com.WangWick.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Handler {
    LoginController loginController;

    public Handler(LoginController loginController){

        this.loginController = loginController;
    }

    public Handler() {
        loginController = new LoginController(new UserService());
    }

    public FrontController route(HttpServletRequest req, HttpServletResponse res){
        switch (req.getRequestURI()){
            case "/W3/login":
                return loginController;
        }
        return null;
    }
}
