package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ulti.HibernateUtil;
import pojo.Attendance;
import pojo.Course;

public class AttendanceDAO {
	public static List<Attendance> getAttendanceList() {
		List<Attendance> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select atd from Attendance atd";
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
	
	
	public static Attendance getAttendanceInfo(Integer attendanceID) {
		Attendance attendance = null;
		Session session = HibernateUtil.getSessionFactory()
		.openSession();
		try {
		attendance = (Attendance) session.get(Attendance.class, attendanceID);
		} catch (HibernateException ex) {
		//Log the exception
		System.err.println(ex);
		} finally {
		session.close();
		}
		return attendance;
		}
	
	public static boolean addNew(Attendance c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		/*if (AttendanceDAO.getAttendanceInfo(c.getmAttendanceID()) != null) {
			return false;
		}*/
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
	public static boolean update(Attendance c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		/*if (AttendanceDAO.getAttendanceInfo(c.getmAttendanceID()) != null) {
			return false;
		}*/
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(c);
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
	
	public static boolean delete(Integer attendanceID) { 
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Attendance a = AttendanceDAO.getAttendanceInfo(attendanceID); 
		if(a==null){ 
			return false; 
		} 
		Transaction transaction = null; 
		try { 
			transaction = session.beginTransaction(); 
			session.delete(a); 
			transaction.commit(); 
		} catch (HibernateException ex) { 
			//Log the exception 
			transaction.rollback(); 
			System.err.println(ex); 
		} finally { 
			session.close(); 
		} 
		return true;
	}

}
