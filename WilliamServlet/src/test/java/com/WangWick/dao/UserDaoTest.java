package com.WangWick.dao;


import com.WangWick.model.User;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    @Test
    @Order(1)
    void saveUserTest() {
        UserDao userDao = new UserDao();

        for (int i = 2; i <= 50; i++) {
            userDao.insert(new User(i));
        }
    }

    @Test
    @Order(2)
    void getAllTest() {
        UserDao userDao = new UserDao();
        List<User> list = userDao.getList();

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    @Order(3)
    void getAllById() {
        UserDao userDao = new UserDao();
        List<User> list = userDao.getList();

        for (User user : list) {
            User user1 = userDao.getById(user.getUser_id());
            Assertions.assertTrue(user.equals(user1));

            user1 = userDao.getByUserId(user.getUser_id()).get(0);
            Assertions.assertTrue(user.equals(user1));

            user1 = userDao.getByUsername(user.getUsername());
            Assertions.assertTrue(user.equals(user1));
        }
    }

    @Test
    @Order(4)
    void getNoUserTest() {
        UserDao userDao = new UserDao();
        User user = userDao.getById(-1);
        Assertions.assertNull(user);
    }

    @Test
    @Order(6)
    void deleteUserTest() {
        UserDao userDao = new UserDao();
        List<User> list = userDao.getList();

        for (User user : list) {
            userDao.delete(user);
        }
    }
}