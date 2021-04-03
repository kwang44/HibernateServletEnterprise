package com.WangWick.controller;

import com.WangWick.service.ReimbursementService;
import com.WangWick.service.UserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontController {
    UserService userService;
    protected static Gson gson = new Gson();
    ReimbursementService reimbursmentService;

    /**
     * Should be implemented by Controllers that handle only one type of request
     * or need to perform complex logic to route handling within controller logic.
     *
     * When possible this second use should be avoided and handle() left abstract,
     * with the Handler directly calling the correct method of the Controller to process
     * the request.
     * @param req
     * @param res
     */
    public abstract void handle(HttpServletRequest req, HttpServletResponse res);
}
