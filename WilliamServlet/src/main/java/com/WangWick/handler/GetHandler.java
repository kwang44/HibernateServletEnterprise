package com.WangWick.handler;

import com.WangWick.controller.ReimbursementController;
import com.WangWick.controller.UserController;
import com.WangWick.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetHandler extends Handler {
    public GetHandler() {
        this.userController = new UserController();
        this.reimbursementController = new ReimbursementController();
    }

    @Override
    public void route(HttpServletRequest req, HttpServletResponse res) {
         String uri = req.getRequestURI();
        System.out.println(uri);
        String params = req.getQueryString();





        switch (uri){
            case "/login":
//                loginController.handle(req, res); //unused: login controller should only handle POST requests
                ServletUtil.sendGenericFailResponse(res);
                break;
            case "/users":
            {
                if(params == null)
                    userController.getAllUsers(req,res);
                else
                    userController.getSingleUser(req,res, params);
            }
                break;

            case"/users/reimbursements":
            {
                if(params== null)
                    reimbursementController.getAllReimbursements(req,res);
                else
                    reimbursementController.getReimbursementsByUserId(req,res,params);
            }
        }

    }
}
