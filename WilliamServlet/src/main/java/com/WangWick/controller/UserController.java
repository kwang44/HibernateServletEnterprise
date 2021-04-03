package com.WangWick.controller;

import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.WangWick.util.HibernateUtil;
import com.WangWick.util.ServletUtil;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class UserController extends FrontController{

    public UserController() {
        this.userService = new UserService();

    }
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) {



    }

    public void createNewUser(HttpServletRequest req, HttpServletResponse res) {
        try {
            String body = HibernateUtil.parseHttpBody(req.getReader());
            if(body.equals("")){
                ServletUtil.sendGenericFailResponse(res);
                return;
            }
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
        HttpSession session = req.getSession(false);
        if(session == null)
        {
            ServletUtil.sendNoSessionResponse(res);
            return;
        }
        User sessionUser = ServletUtil.getSessionUser(session);
        if(sessionUser.getRole_id()!=1)
        {
            ServletUtil.sendUnauthorizedError(res);
            return;
        }
        List<User> users = userService.fetchAllUsers();
        res.setContentType("application/json");
        try {
            res.getWriter().print(gson.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSingleUser(HttpServletRequest req, HttpServletResponse res, String params) {
        HttpSession session = req.getSession(false);
        if(session == null)
        {
            ServletUtil.sendNoSessionResponse(res);
            return;
        }
        User sessionUser = ServletUtil.getSessionUser(session);
            try {
                User result = null;
                Map<String, String> queryMap = ServletUtil.mapQuery(params);
                if(queryMap.containsKey("username"))
                    result = userService.getUserByUsername(queryMap.get("username"));
                if (result!=null){
                    if(sessionUser.getRole_id()==1 || (result.getUser_id() == sessionUser.getUser_id())){
                        res.setContentType("application/json");
                        res.getWriter().print(gson.toJson(result));
                    }else {
                        ServletUtil.sendUnauthorizedError(res);
                    }
                }
                else
                    ServletUtil.sendGenericFailResponse(res);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

