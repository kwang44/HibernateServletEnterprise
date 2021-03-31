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

    /**
     * Also act as a set method for this singleton
     * @param fileName
     * @return a sessionFactory
     */
    public static SessionFactory setSessionFactory(String fileName) {
        Configuration configuration = new Configuration();
        configuration.configure(fileName);
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
