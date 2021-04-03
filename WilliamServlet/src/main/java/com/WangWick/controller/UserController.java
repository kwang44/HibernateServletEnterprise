package com.WangWick.controller;

import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.WangWick.util.HibernateUtil;
import com.WangWick.util.ServletUtil;
import com.google.gson.Gson;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class UserController extends FrontController{
    UserService userService;
    Gson gson;

    public UserController() {
        this.userService = new UserService();
        this.gson = new Gson();

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
            System.out.println(req.getContentType());
            if(!req.getContentType().equals("text/json") &&
                    !req.getContentType().equals("application/json")){
                res.sendError(400, "This failure should be more descriptive than it is, but less than a 500");
                return;
            }
            User newUser = gson.fromJson(body,User.class);
            if(newUser != null){
                try {
                    userService.register(newUser);
                } catch (Exception e) {
                    res.sendError(409, "Could not register");
                    return;
                }

                res.getWriter().print("{\"userCreated\":\"true\",\"userId\":" +
                        userService.getUserByUsername(newUser.getUsername()).getUser_id() +"}"
                );
            }else
                res.getWriter().print("{\"userCreated\":false}");


    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers(HttpServletRequest req, HttpServletResponse res) {
        List<User> users = userService.fetchAllUsers();
        res.setContentType("application/json");
        try {
            res.getWriter().print(gson.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSingleUser(HttpServletRequest req, HttpServletResponse res, String params) {
        try {
            User result = null;
            Map<String, String> queryMap = ServletUtil.mapQuery(params);
            if(queryMap.containsKey("username"))
                result = userService.getUserByUsername(queryMap.get("username"));
            if (result!=null){
                res.setContentType("application/json");
                res.getWriter().print(gson.toJson(result));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
