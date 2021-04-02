package com.WangWick.controller;

import com.WangWick.model.LoginAttempt;
import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

public class LoginController extends FrontController {
    UserService us;
    Gson gb;

    public LoginController(UserService us) {
        this.us = us;
        this.gb = new Gson();
    }

    public LoginController() {
    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) {

        try {
            LoginAttempt attempt = gb.fromJson(req.getReader(), LoginAttempt.class);
            User u = us.getUserByLogin(attempt.getUsername(),attempt.getPassword());
            if(u!=null){
                HttpSession session= req.getSession(true);
/*                                          if we make sure to check that valid session already
                                            exists by using req.getSession(false) everywhere else we force the user
                                            to login after 15 minutes of inactivity and lets us store role info in session
                                            to restrict use of different functions.
*/
                session.setAttribute("user",u);
                res.setContentType("text/json");
                res.getWriter().print("{\"id\":"+gb.toJson(session.getId())+"}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}