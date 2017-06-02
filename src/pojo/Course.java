package pojo;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;



public class Course{
	private String mCourseID;
	private String mName;
	private Date mDateStarted;
	private Date mDateFinished;
	private int mWeekday;
	private Time mTimeStarted;
	private Time mTimeFinished;
	private String mClassroom;
	private Set<Attendance> mAttendances = new HashSet<Attendance>(0);
	


	// Constructors
	public Course(){
		
	}
	
	public Course(String courseID, String name, 
			Date dateStarted, Date dateFinished,
			int weekday, Time timeStarted, Time timeFinished, String classroom){
		this.mCourseID = courseID;
		this.mName = name;
		this.mDateStarted = dateStarted;
		this.mDateFinished = dateFinished;
		this.mWeekday = weekday;
		this.mTimeStarted = timeStarted;
		this.mTimeFinished = timeFinished;
	}
	// Getters, setters

	public String getmCourseID() {
		return this.mCourseID;
	}

	public void setmCourseID(String mCourseID) {
		this.mCourseID = mCourseID;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Date getmDateStarted() {
		return this.mDateStarted;
	}

	public void setmDateStarted(Date mDateStarted) {
		this.mDateStarted = mDateStarted;
	}

	public Date getmDateFinished() {
		return mDateFinished;
	}

	public void setmDateFinished(Date mDateFinished) {
		this.mDateFinished = mDateFinished;
	}

	public int getmWeekday() {
		return mWeekday;
	}

	public void setmWeekday(int mWeekday) {
		this.mWeekday = mWeekday;
	}

	public Time getmTimeStarted() {
		return mTimeStarted;
	}

	public void setmTimeStarted(Time mTimeStarted) {
		this.mTimeStarted = mTimeStarted;
	}

	public Time getmTimeFinished() {
		return mTimeFinished;
	}

	public void setmTimeFinished(Time mTimeFinished) {
		this.mTimeFinished = mTimeFinished;
	}

	public String getmClassroom() {
		return mClassroom;
	}

	public void setmClassroom(String mClassroom) {
		this.mClassroom = mClassroom;
	}
	
	public Set<Attendance> getmAttendances() {
		return mAttendances;
	}

	public void setmAttendances(Set<Attendance> mAttendances) {
		this.mAttendances = mAttendances;
	}
	
	public static Vector<String> getTableRow(int i, List<Course> listCourse){
		Vector<String> res = new Vector<String>();
		Course course = listCourse.get(i);
		res.addElement(course.getmCourseID());
		res.addElement(course.getmName());
		Date currentDate = new Date();
		if (course.getmDateFinished().compareTo(currentDate)< 0){
			res.addElement("Đã kết thúc");
		}
		else if (course.getmDateStarted().compareTo(currentDate)>0){
			res.addElement("Chưa bắt đầu");
		}else {
			res.addElement("Đang diễn ra");
		}
		res.addElement("Chỉnh sửa");
		return res;
	}

	
}
