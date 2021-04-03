package com.WangWick.dao;

import java.util.List;

import com.WangWick.model.User;
import com.WangWick.util.HibernateUtil;

import com.WangWick.model.Reimbursement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class ReimbursementDao implements com.WangWick.dao.GenericDao<Reimbursement> {


    @Override
    public List<Reimbursement> getList() {
        List<Reimbursement> reimbursementList = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            reimbursementList = session.createQuery("FROM reimbursements").list();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public Reimbursement getById(int id) {
        Reimbursement reimbursement = null;
        Transaction transaction = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            reimbursement = session.get(Reimbursement.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return reimbursement;
    }

    @Override
    public List<Reimbursement> getByUserId(int id) {
        List<Reimbursement> reimbursementList = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM reimbursements R WHERE R.author = :id");
            User user = new User();
            user.setUser_id(id);
            query.setParameter("id", user);
            reimbursementList = query.list();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    public Reimbursement getByUsername(String username) {
        //Empty. Reason - No use.
        return null;
    }

    @Override
    public void insert(Reimbursement r) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(r);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();;
        }
    }

    @Override
    public void delete(Reimbursement r) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(r);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Reimbursement reimbursement) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(reimbursement);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
