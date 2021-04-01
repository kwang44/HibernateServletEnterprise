package com.WangWick.dao;


import com.WangWick.model.User;
import com.WangWick.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    @Test
    @Order(1)
    void saveUserTest() {
        UserDao userDao = new UserDao();

        for (int i = 2; i <= 50; i++) {
            userDao.insert(new User(i));
        }
        HibernateUtil.getSessionFactory().openSession().delete();
    }

    @Test
    @Order(2)
    void getAllTest() {
        
    }

    @Test
    @Order(3)
    void deleteUserTest() {
        UserDao userDao = new UserDao();

        for (int i = 1; i < 50; i++) {
            userDao.delete(new User(i));
        }
    }
}