package pojo;

import java.util.Date;

public class Attendance {
	private Integer mAttendanceID;
	//private String mStudentID;
	//private String mCourseID;
	private int mWeek;
	private Date mDate;
	private int mStatus;
	
	private User mStudent;
	private Course mCourse;
	
	//Constructors
	public Attendance(){
		
	}
	
	public Attendance(Integer attendanceID, User student, Course course,
						int week, Date date, int status){
		this.mAttendanceID = attendanceID;
		this.mStudent = student;
		this.mCourse = course;
		this.mWeek = week;
		this.mDate = date;
		this.mStatus = status;
	}



	//Getters, Setters
	/*
	public String getmStudentID() {
		return mStudentID;
	}

	public void setmStudentID(String mStudentID) {
		this.mStudentID = mStudentID;
	}

	public String getmCourseID() {
		return mCourseID;
	}

	public void setmCourseID(String mCourseID) {
		this.mCourseID = mCourseID;
	}*/

	public int getmWeek() {
		return mWeek;
	}

	public Integer getmAttendanceID() {
		return mAttendanceID;
	}



	public void setmAttendanceID(Integer mAttendanceID) {
		this.mAttendanceID = mAttendanceID;
	}



	public void setmWeek(int mWeek) {
		this.mWeek = mWeek;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public int getmStatus() {
		return mStatus;
	}

	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}

	public User getmStudent() {
		return mStudent;
	}

	public void setmStudent(User mStudent) {
		this.mStudent = mStudent;
	}

	public Course getmCourse() {
		return mCourse;
	}

	public void setmCourse(Course mCourse) {
		this.mCourse = mCourse;
	}
	
	

}
