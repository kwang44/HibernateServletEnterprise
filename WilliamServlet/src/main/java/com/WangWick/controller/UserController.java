package com.WangWick.controller;

import com.WangWick.service.UserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends FrontController{
    UserService userService;
    Gson gson;

    public UserController() {

    }

    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }



    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) {

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
