package entity;

import java.util.Calendar;

import control.UserControl;

public class UserTest {

	public static void main(String args[]){
		User user = new User("123456789","dnuahf","nfeh@sdbh.w");
		String studentID= user.getStudentID();
		System.out.println(studentID);
	}
}
