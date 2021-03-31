package com.WangWick.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.WangWick.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao<User> {
    private SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);


    @Override
    public List<User> getList() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        List<User> l = session.createQuery(criteria).getResultList();
        LOGGER.debug("A list of users was retrieved from the database.");
        return l;
    }
//		try (Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users";
//			Statement s = c.createStatement();
//			ResultSet rs = s.executeQuery(qSql);
//
//			while(rs.next()) {
//				l.add(objectConstructor(rs));
//			}
//			LOGGER.debug("A list of users was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get all users from the database failed.");
//		}
//		return l;
//	}
//
	@Override
	public User getById(int id) {
		Session session = sessionFactory.getCurrentSession();
        User u = session.get(User.class,id);

//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//				u = objectConstructor(rs);
//
        LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
        return u;
    }

	@Override
	public List<User> getByUserId(int id) { //right now this is a more complicated way of doing the above getById as id is unique
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("user_id"),id));

        return session.createQuery(criteria).getResultList();

	}

	@Override
	public User getByUsername(String username) {
		User u;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("username"),username));
        u = session.createQuery(criteria).getSingleResult();

//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
//			String qSql = "SELECT * FROM ers_users WHERE ers_username = ?";
//			PreparedStatement ps = c.prepareStatement(qSql);
//			ps.setString(1, username.toLowerCase());
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next()) {
//				//System.out.println("User object was created!");
//				u = objectConstructor(rs);
//			}
//
			LOGGER.debug("Information about username " + username + " was retrieved from the database.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
//		}
		return u;
	}

	@Override
	public void insert(User t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);

    }



	@Override
	public void delete(User t) {
        sessionFactory.getCurrentSession().delete(t);

	}


        public void setSessionFactory (SessionFactory sessionFactory){
            this.sessionFactory = sessionFactory;
        }
    }
