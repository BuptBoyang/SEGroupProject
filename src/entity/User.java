package entity;

import java.util.regex.*;

public class User {
	private String studentID;
	private String email;
	private String name;
	private float fine;
	private int status;

	public User(String id, String name , String email) {
		this.email = email;
		this.name = name;
		this.studentID = id;
	}

	public boolean isIDLegal() {
		if (this.studentID.length() == 9) {
			try {
				Integer.valueOf(this.studentID);
				return true;
			} catch (Exception ex) {
			}
		}
		return false;
		// the QM number should be 9 digits;
		/*
		 * String REGEX="^\d{9}$"; Pattern p = Pattern.compile(REGEX); Matcher
		 * matcher=p.matcher(this.studentID); return matcher.matches();
		 */
	}

	public boolean isEmailLegal() {
		// email address must in the form of xxx@xxx.xxx,
		//"[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?@[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?"
		String REGEX="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(REGEX);
		Matcher matcher = p.matcher(this.email);
		return matcher.matches();
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [studentID=" + studentID + ", email=" + email + ", name=" + name + "]";
	}

	public float getFine() {
		return fine;
	}

	public void setFine(float fine) {
		this.fine = fine;
	}

}
