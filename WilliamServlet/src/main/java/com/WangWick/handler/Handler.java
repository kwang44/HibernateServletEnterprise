package com.WangWick.handler;

import com.WangWick.controller.LoginController;
import com.WangWick.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {
    LoginController loginController;
    UserController userController;

    public Handler() {
        loginController = new LoginController();
        userController = new UserController();
    }

    public abstract void route(HttpServletRequest req, HttpServletResponse res);

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
