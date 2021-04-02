package com.WangWick.controller;

import com.WangWick.model.User;
import com.WangWick.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import static org.mockito.Mockito.*;

class LoginControllerTest {


    @Mock
    UserService userService = mock(UserService.class);
    @Mock
    User u = mock(User.class);
    @Mock
    HttpServletRequest req =mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse res = mock(HttpServletResponse.class);
    @Mock
    HttpSession session = mock(HttpSession.class);
    @Mock
    PrintWriter printWriter = mock(PrintWriter.class);
    @InjectMocks
    LoginController loginController;

    @BeforeEach
    void setUp() {
        try {
            //Test subject
            loginController = new LoginController();
            //Cannot mock GSon
            loginController.setGson(new Gson());
            //Can mock our utilities
            loginController.setUserService(userService);

            //Mocks proper body of login POST REQUEST
            when(req.getReader()).thenReturn(
                    new BufferedReader(
                            new StringReader(
                                    "{\"username\":\"JohnyTest\",\n" +
                                            "\"password\":\"pass\"}\n")));

            //Mocks response from successful login validation
            when(userService.getUserByLogin("JohnyTest","pass")).thenReturn(
                    u);
            //Mocks session creation and retrieval
            when(req.getSession(true)).thenReturn(session);
            //Mocks HTTP Response body writer
            when(res.getWriter()).thenReturn(printWriter);
            //Mocks accessing session bound attribute
            when(session.getAttribute("user")).thenReturn(u);
            //Mocks accessing session bound attribute data
            when(u.getUsername()).thenReturn("JohnyTest");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @AfterEach
    void tearDown() {
        reset(userService,u,req,res,session,printWriter);
    }

    @Test
    void handle() {

        loginController.handle(req,res);
        HttpSession testSession = req.getSession(true);
        User actual = (User) testSession.getAttribute("user");

        Assertions.assertEquals("JohnyTest",actual.getUsername());


    }
}