package com.WangWick.dao;
import java.util.List;

import com.WangWick.util.HibernateUtil;

import com.WangWick.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao<User> {


    @Override
    public List<User> getList() {
        List<User> userList = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM users").list();
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
            Query query = session.createQuery("FROM users U WHERE U.id = :id");
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
            Query query = session.createQuery("FROM users U WHERE U.username = :username");
            query.setParameter("username", username);
            user = (User) query.uniqueResult();
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
	public void insert(User t) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }



	@Override
	public void delete(User t) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
