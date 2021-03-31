package com.WangWick.dao;

import com.WangWick.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserDaoTest {

    static SessionFactory sessionFactory;
    static User testUser;
    @BeforeEach
    void setUp() {
        Configuration cfg = new Configuration()
                .configure("hibernate_test.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();

        User u = new User();
        u.setUsername("UserNameTest");
        u.setFirstname("Johny");
        u.setLastname("Tester");
        u.setEmail("test@test.test");
        u.setRole_id(1);
        u.setPassword("password");
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.persist(u);
        session.flush();
        testUser = u;


    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
        testUser = null;
    }

    @Test
    void getList() {
        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        List<User> expected = new LinkedList<User>();
        expected.add(testUser);
        Assertions.assertEquals(expected, ud.getList());
    }

    @Test
    void getById() {
        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        Assertions.assertEquals(testUser,ud.getById(testUser.getUser_id()));
    }

    @Test
    void getByUserId() {
        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        List<User> expected = new LinkedList<User>();
                expected.add(testUser);
        Assertions.assertEquals(expected, ud.getByUserId(testUser.getUser_id()));
    }

    @Test
    void getByUsername() {
        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        Assertions.assertEquals(testUser,ud.getByUsername(testUser.getUsername()));
    }

    @Test
    void insert() {

        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        Assertions.assertDoesNotThrow(()->ud.insert(testUser));
    }

    @Test
    void delete() {
        UserDao ud = new UserDao();
        ud.setSessionFactory(sessionFactory);
        Assertions.assertDoesNotThrow(()->ud.delete(testUser));
    }
}