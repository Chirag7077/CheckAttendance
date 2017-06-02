package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ulti.HibernateUtil;
import pojo.Course;

public class CourseDAO {
	public static List<Course> getCourseList() {
		List<Course> courseList = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select course from Course course";
			Query query = session.createQuery(hql);
			courseList = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return courseList;
	}

	public static Course getCourse(String courseID) {
		Course c = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			c = (Course) session.get(Course.class, courseID);
		} catch (HibernateException ex) {
			// Log the exception System.err.println(ex);
		} finally {
			session.close();
		}
		return c;
	}

	public static boolean addNew(Course c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (CourseDAO.getCourse(c.getmCourseID()) != null) {
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
	
	public static boolean update (Course c) { 
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		if (CourseDAO.getCourse(c.getmCourseID()) == null) { 
			return false; 
		} 
		Transaction transaction = null; 
		try { 
			transaction = session.beginTransaction(); 
			session.update(c); 
			transaction.commit(); 
		} catch (HibernateException ex) { 
			//Log the exception 
			transaction.rollback(); 
			System.err.println(ex); 
		} finally { 
				session.close(); 
		} return true;
	}

}
