package com.WangWick.util;

import com.WangWick.model.User;

import javax.activity.InvalidActivityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServletUtil {
    public static Map<String, String> mapQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    public static void sendGenericFailResponse(HttpServletResponse res) {
        try {
            res.sendError(400, "A generic problem occurred because something was wrong with your request.\n" +
                    "See the design aids in W3 documentation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendNoSessionResponse(HttpServletResponse res) {

        try {
            res.sendError(401, "This resource requires login at /W3/login");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUnauthorizedError(HttpServletResponse res) {

        try {
            res.sendError(403, "Your credentials are not authorized for this resource\n" +
                    "verify login or contact administrator");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static User getSessionUser(HttpSession session) {
        return (User) session.getAttribute("user");

    }

    public static void sendStateConflictResponse(HttpServletResponse res, String message) {
        try {
            res.sendError(409,message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

