package com.WangWick.controller;

import com.WangWick.model.Reimbursement;
import com.WangWick.model.ReimbursementUpdateModel;
import com.WangWick.model.User;
import com.WangWick.service.ReimbursementService;
import com.WangWick.service.UserService;
import com.WangWick.servlet.ReimbursementServlet;
import com.WangWick.util.HibernateUtil;
import com.WangWick.util.ServletUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReimbursementController extends FrontController {
    public ReimbursementController() {
        this.userService = new UserService();
        this.reimbursmentService = new ReimbursementService();
    }

    public void getAllReimbursements(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            ServletUtil.sendNoSessionResponse(res);
            return;
        }
        User sessionUser = ServletUtil.getSessionUser(session);
        if (sessionUser.getRole_id() != 1) {
            ServletUtil.sendUnauthorizedError(res);
            return;
        }
        List<Reimbursement> reimbursements = reimbursmentService.fetchAllReimbursement();
        res.setContentType("application/json");
        try {
            res.getWriter().print(gson.toJson(reimbursements));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getReimbursementsByUserId(HttpServletRequest req, HttpServletResponse res, String params) {
        HttpSession session = req.getSession(false);
        if (session == null) //validate session
        {
            ServletUtil.sendNoSessionResponse(res);
            return;
        }
        User sessionUser = ServletUtil.getSessionUser(session); //gather session info
        try {
            List<Reimbursement> result = null;
            Map<String, String> queryMap = ServletUtil.mapQuery(params); //map parameters
            int queryId = Integer.parseInt(queryMap.get("id"));//get id or null
            if (queryId != 0) {
                result = reimbursmentService.getReimbursementsById(queryId);

                if ((sessionUser.getRole_id() == 1) || queryId == sessionUser.getUser_id()) {
                    res.setContentType("application/json");
                    res.getWriter().print(gson.toJson(result));
                } else {
                    ServletUtil.sendUnauthorizedError(res);
                }
            } else {
                ServletUtil.sendGenericFailResponse(res);
            }
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServletUtil.sendGenericFailResponse(res);

    }


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) {

    }

    public void createNewReimbursement(HttpServletRequest req, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                ServletUtil.sendNoSessionResponse(res);
                return;
            }
            User sessionUser = ServletUtil.getSessionUser(session);

            String body = HibernateUtil.parseHttpBody(req.getReader());
            if (body.equals("")) {
                ServletUtil.sendGenericFailResponse(res);
                return;
            }
            if (!req.getContentType().equals("text/json") &&
                    !req.getContentType().equals("application/json")) {
                res.sendError(400, "Body type should be text/json");
                return;
            }
            Reimbursement newReimbursement = gson.fromJson(body, Reimbursement.class);
            if (newReimbursement != null) {
                try {
                    newReimbursement.setAuthor(sessionUser);
                    newReimbursement.setSubmitted(new Timestamp(System.currentTimeMillis()));
                    newReimbursement.setStatus_id(0);
                    reimbursmentService.generateReimbursement(newReimbursement);

                } catch (Exception e) {
                    res.sendError(409, "Could not register");
                    return;
                }

                res.getWriter().print("{\"registrationCreated\":\"true\",\"authorId\":" +
                        sessionUser.getUser_id() + "}"

                );
            } else
                res.getWriter().print("{\"registrationCreated\":false}");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resolveReimbursements(HttpServletRequest req, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                ServletUtil.sendNoSessionResponse(res);
                return;
            }
            User sessionUser = ServletUtil.getSessionUser(session);
            if (sessionUser.getRole_id() != 1) {
                ServletUtil.sendUnauthorizedError(res);
                return;
            }
            String body = HibernateUtil.parseHttpBody(req.getReader());
            if (body.equals("")) {
                ServletUtil.sendGenericFailResponse(res);
                return;
            }

            ReimbursementUpdateModel[] resolveA;
            resolveA = gson.fromJson(body, ReimbursementUpdateModel[].class);

            Arrays.stream(resolveA).forEach((r) -> {
                Reimbursement current = reimbursmentService.getReimbursementById(r.getId());
                if (current!=null){
                current.setResolver(sessionUser);
                current.setResolved(new Timestamp(System.currentTimeMillis()));
                current.setStatus_id(r.isApproved() ? 1 : 2);
                reimbursmentService.updateReimbursement(current);
            }});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}