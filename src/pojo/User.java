package pojo;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String mUsername;
	private String mPassword;
	private Integer mType;
	private String mFullname;
	public Integer getmType() {
		return mType;
	}
	public void setmType(Integer mType) {
		this.mType = mType;
	}
	private Set<Attendance> mAttendances = new HashSet<Attendance>(0);
	
	//Constructors
	public User(){
		
	}
	public Set<Attendance> getmAttendances() {
		return mAttendances;
	}
	public void setmAttendances(Set<Attendance> mAttendances) {
		this.mAttendances = mAttendances;
	}
	public String getmFullname() {
		return mFullname;
	}
	public void setmFullname(String mFullname) {
		this.mFullname = mFullname;
	}
	public String getmUsername() {
		return mUsername;
	}
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

}
