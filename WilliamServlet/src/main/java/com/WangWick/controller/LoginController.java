package com.WangWick.controller;

import com.WangWick.model.LoginAttempt;
import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.WangWick.util.HibernateUtil;
import com.google.gson.Gson;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends FrontController {
    UserService userService;
    Gson gson;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public LoginController() {
        userService = new UserService();
        this.gson = new Gson();
    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) {

        try {
             String body = HibernateUtil.parseHttpBody(req.getReader());
             if(body.equals("")){
                 //TODO:
             }
            LoginAttempt attempt = gson.fromJson(body, LoginAttempt.class);
            User u = userService.getUserByLogin(attempt.getUsername(),attempt.getPassword());
            if(u!=null){
                HttpSession session= req.getSession(true);
/*                                          if we make sure to check that valid session already
                                            exists by using req.getSession(false) everywhere else we force the user
                                            to login after 15 minutes of inactivity and lets us store role info in session
                                            to restrict use of different functions.
*/
                session.setAttribute("user",u);
                res.setContentType("text/json");
                res.getWriter().print("{\"id\":"+ gson.toJson(session.getId())+"}");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}