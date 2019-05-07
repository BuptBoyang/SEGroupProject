package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entity.User;

public class UserControl {

	private ArrayList<User> userArrayList;

	public UserControl() {
		userArrayList = new ArrayList<User>();
		read();
	}

	public boolean isIDLegal(String studentID) {
		if (studentID.length() == 9) {
			try {
				Integer.valueOf(studentID);
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

	public boolean isEmailLegal(String email) {
		// email address must in the form of xxx@xxx.xxx,
		// "[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?@[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?"
		String REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(REGEX);
		Matcher matcher = p.matcher(email);
		return matcher.matches();
	}

	public boolean isDuplication(String id) {
		for (int i = 0; i < userArrayList.size(); i++) {

			if (userArrayList.get(i).getStudentID().equals(id)) {
				return false;
			}
		}
		return true;
	}

	public void register(User user) {
		userArrayList.add(user);
		System.out.println(user);
	}

	public void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("D:\\userList.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				User user = new User(item[0], item[1], item[2]);
				register(user);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void write() {
		try {
			File csv = new File("D:\\userList.csv");

			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			for (int i = 0; i < userArrayList.size(); i++) {
				bw.write(userArrayList.get(i).getStudentID() + "," + userArrayList.get(i).getName() + ","
						+ userArrayList.get(i).getEmail());
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int searchID(String studentID) {
		for (int i = 0; i < userArrayList.size(); i++) {
			if (userArrayList.get(i).getStudentID() == studentID) {
				return i;
			}
		}
		return 0;
	}

	public void takeScooter(String studentID) {
		int i = searchID(studentID);
		Calendar cld = Calendar.getInstance();
		userArrayList.get(i).setStatus(1);//1 means using now
		userArrayList.get(i).setCld(cld);
	}

	public int[] usingTime(String studentID) {
		int i=searchID(studentID);
		int[] time=new int[2];
		Calendar now=Calendar.getInstance();
		Calendar last=userArrayList.get(i).getCld();
		int minute=(now.get(Calendar.YEAR)-last.get(Calendar.YEAR))*525600+
				(now.get(Calendar.DAY_OF_YEAR)-last.get(Calendar.DAY_OF_YEAR));
		userArrayList.get(i).setStatus(2);//2 means no using 
		time[0]=minute;
		int[] todayUsingTime=userArrayList.get(i).getDayUsingTime();
		if(todayUsingTime[0]==now.get(Calendar.DAY_OF_YEAR)) {
			todayUsingTime[1]+=minute;
		}else
			todayUsingTime[1]=minute;
		userArrayList.get(i).setDayUsingTime(now.get(Calendar.DAY_OF_YEAR), todayUsingTime[1]);
		time[1]=todayUsingTime[1];
		return time;
	}
	
	public void ban(String studentID) {
		int i=searchID(studentID);
		if(i>=0) {
			userArrayList.get(i).setFine(true);
		}else
			System.out.println("something wrong");
	}
	
	public boolean usingStatus(String studentID) {
		int i= searchID(studentID);
		if (userArrayList.get(i).getStatus()==1) {
			return true;
		}else
			return false;	
	}
	
	public void unBan(String studentID) {
		int i=searchID(studentID);
		if(i>=0) {
			userArrayList.get(i).setFine(false);
		}else
			System.out.println("something wrong");
	}
}
