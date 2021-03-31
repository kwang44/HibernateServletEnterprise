package com.WangWick.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(String fileName) {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure(fileName);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
