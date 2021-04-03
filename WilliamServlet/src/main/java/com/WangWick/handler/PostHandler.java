package com.WangWick.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostHandler extends Handler {
    @Override
    public void route(HttpServletRequest req, HttpServletResponse res) {

            switch (req.getRequestURI()){
                case "/W3/login":
                    loginController.handle(req, res);
                    break;
                case "/W3/users":
                    userController.createNewUser(req,res);
                    break;
            }
        }

}
