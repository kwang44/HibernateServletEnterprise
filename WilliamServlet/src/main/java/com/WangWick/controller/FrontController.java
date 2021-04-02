package com.WangWick.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontController {
    public abstract void handle(HttpServletRequest req, HttpServletResponse res);
}
