package com.WangWick.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHandler extends Handler {
    @Override
    public void route(HttpServletRequest req, HttpServletResponse res) {
         String uri = req.getRequestURI();
        System.out.println(uri);
        String params = req.getQueryString();





        switch (uri){
            case "/W3/login":
//                loginController.handle(req, res); //unused: login controller should only handle POST requests
                break;
            case "/W3/users":
            {
                if(params == null)
                    userController.getAllUsers(req,res);
                else
                    userController.getSingleUser(req,res, params);
            }
                break;
        }

    }
}
