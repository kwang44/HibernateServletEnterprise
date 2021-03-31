package com.WangWick.dao;

import java.net.URI;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.WangWick.model.User;
import org.apache.log4j.Logger;

import com.WangWick.model.Reimbursement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class ReimbursementDao implements com.WangWick.dao.GenericDao<Reimbursement> {
    private SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(ReimbursementDao.class);


    @Override
    public List<Reimbursement> getList() {
        List<Reimbursement> l = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> criteria = builder.createQuery(Reimbursement.class);
            Root<Reimbursement> root = criteria.from(Reimbursement.class);
            criteria.select(root);
            l = session.createQuery(criteria).getResultList();
            LOGGER.debug("A list of reimbursement was retrieved from the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to get all reimbursements failed.");
        }
        return l;

    }


    @Override
    public Reimbursement getById(int id) {
        Reimbursement r = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            r = session.get(Reimbursement.class, id);
            LOGGER.debug("A reimbursement by ID " + id + " was retrieved from the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to get a reimbursement by ID" + id + " from the database failed.");
        }

//
//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//				r = objectConstructor(rs);
//
//			rs.close();
//			ps.closeOnCompletion();
//			LOGGER.debug("A reimbursement by ID " + id + " was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get a reimbursement by ID" + id + " from the database failed.");
//		}
        return r;
    }

    @Override
    public List<Reimbursement> getByUserId(int id) {
        List<Reimbursement> l = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> criteria = builder.createQuery(Reimbursement.class);
            Root<Reimbursement> root = criteria.from(Reimbursement.class);
            criteria.select(root).where(builder.equal(root.get("reimb_author"), id));

            l = session.createQuery(criteria).getResultList();
            LOGGER.debug("A list of reimbursements made by user ID " + id + " was retrieved from the database.");
            return l;
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to get all reimbursements made by user ID " + id + " fron the database failed.");
        }
//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				l.add(objectConstructor(rs));
//			}
//			rs.close();
//			ps.closeOnCompletion();
//			LOGGER.debug("A list of reimbursements made by user ID " + id + " was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get all reimbursements made by user ID " + id + " fron the database failed.");
//		}
//		System.out.println(l.toString());
        return l;
    }

    public Reimbursement getByUsername(String username) {
        //Empty. Reason - No use.
        return null;
    }

    @Override
    public void insert(Reimbursement r) {

        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(r);
            LOGGER.debug("A new reimbursement was successfully added to the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to insert a reimbursement to the database failed.");
        }

    }

    @Override
    public void delete(Reimbursement r) {
        try {
            sessionFactory.getCurrentSession().delete(r);
            LOGGER.debug("A reimbursement was successfully added to the database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error("An attempt to remove a reimbursement from the database failed.");
        }

    }
}
