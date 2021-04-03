package com.WangWick.handler;

import com.WangWick.controller.ReimbursementController;
import com.WangWick.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutHandler extends Handler {
    public PutHandler(){
        reimbursementController = new ReimbursementController();
    }
    @Override
    public void route(HttpServletRequest req, HttpServletResponse res) {
        try {
            switch (req.getRequestURI()){
                case "/W3/login":
                    res.sendError(405,"cannot PUT to login");
                    break;
                case "/W3/users":
                    res.sendError(405,"cannot PUT to users at this time");
                    break;
                case "/W3/users/reimbursements":
                    reimbursementController.resolveReimbursements(req,res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
