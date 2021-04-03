package com.WangWick.handler;

import com.WangWick.controller.LoginController;
import com.WangWick.controller.ReimbursementController;
import com.WangWick.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostHandler extends Handler {
    public PostHandler() {
        loginController = new LoginController();
        userController = new UserController();
        reimbursementController = new ReimbursementController();
    }

    @Override
    public void route(HttpServletRequest req, HttpServletResponse res) {

            switch (req.getRequestURI()){
                case "/W3/login":
                    loginController.handle(req, res);
                    break;
                case "/W3/users":
                    userController.createNewUser(req,res);
                    break;
                case "/W3/users/reimbursements":
                    reimbursementController.createNewReimbursement(req,res);
            }
        }

}
