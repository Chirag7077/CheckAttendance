package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ulti.HibernateUtil;
import pojo.Course;
import pojo.User;

public class UserDAO {
	public static List<User> getUserList() {
		List<User> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select user from User user";
			Query query = session.createQuery(hql);
			ds = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}

	public static User getUserInfo(String userID) {
		User u = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			u = (User) session.get(User.class, userID);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return u;
	}
	
	public static boolean addNew(User c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (UserDAO.getUserInfo(c.getmUsername()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(c);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	
	public static boolean update(User c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (UserDAO.getUserInfo(c.getmUsername()) == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(c);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
}
