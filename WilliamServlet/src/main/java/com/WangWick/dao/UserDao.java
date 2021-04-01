package com.WangWick.dao;
import java.util.List;

import com.WangWick.util.HibernateUtil;
import org.apache.log4j.Logger;

import com.WangWick.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao<User> {
    private SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);


    @Override
    public List<User> getList() {
        List<User> userList = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return userList;
    }

	@Override
	public User getById(int id) {
        User user = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return user;
    }

	@Override
	public List<User> getByUserId(int id) {
        List<User> userList = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User U WHERE U.id = :id");
            query.setParameter("id", id);
            userList = query.list();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return userList;
	}

	@Override
	public User getByUsername(String username) {
        User user = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User U WHERE U.id = :id");
            query.setParameter("id", id);
            userList = query.list();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }

        return userList;
	}

	@Override
	public void insert(User t) {
        try {
            Session session = sessionFactory.getCurrentSession();


            session.persist(t);
            LOGGER.debug("A new user was successfully added to the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to insert a user to the database failed.");
        }

    }



	@Override
	public void delete(User t) {
        try {
            sessionFactory.getCurrentSession().delete(t);
            LOGGER.debug("A new user was successfully added to the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to remove a user from the database failed.");
        }

    }


        public void setSessionFactory (SessionFactory sessionFactory){
            this.sessionFactory = sessionFactory;
        }
    }
