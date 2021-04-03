package com.WangWick.controller;

import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.WangWick.util.HibernateUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public void createNewUser(HttpServletRequest req, HttpServletResponse res) {
        try {
            String body = HibernateUtil.parseHttpBody(req.getReader());
            if(body.equals("")){
                //TODO:reject empty body with correct http res
            }
            User newUser = gson.fromJson(body,User.class);
            if(newUser != null){
                userService.register(newUser);
            }


    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
