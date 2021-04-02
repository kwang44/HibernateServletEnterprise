package com.WangWick.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.Reader;

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

    public static String parseHttpBody(Reader r) throws IOException {
        char[] buffer = new char[4096];
        StringBuilder builder = new StringBuilder();
        int numChars;

        while ((numChars = r.read(buffer)) >= 0) {
            builder.append(buffer, 0, numChars);
        }

        return builder.toString();



    }
}
