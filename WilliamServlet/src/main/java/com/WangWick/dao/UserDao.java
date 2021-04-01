package com.WangWick.dao;
import java.util.List;

import com.WangWick.util.HibernateUtil;
import org.apache.log4j.Logger;

import com.WangWick.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
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
            userList = 
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
