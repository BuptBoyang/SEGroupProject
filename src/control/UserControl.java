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

import entity.Usage;
import entity.User;

public class UserControl {

	public static ArrayList<User> userArrayList = new ArrayList<User>();

	public static boolean isIDLegal(String studentID) {
		if (studentID.length() == 9) {
			try {
				Integer.valueOf(studentID);
				return true;
			} catch (Exception ex) {
			}
		}
		return false;
	}

	public static boolean isEmailLegal(String email) {
		// email address must in the form of xxx@xxx.xxx,
		// "[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?@[a-z0-9A-Z]+(.[a-z0-9A-z]{1,})?"
		String REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(REGEX);
		Matcher matcher = p.matcher(email);
		return matcher.matches();
	}

	public static boolean isDuplication(String id) {
		for (int i = 0; i < userArrayList.size(); i++) {
			if (userArrayList.get(i).getStudentID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static void register(User user) {
		userArrayList.add(user);
		System.out.println(user);
	}

	public static void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("userList.csv"));
//			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				User user = new User(item[0], item[1], item[2]);
				int i = Integer.parseInt(item[3]);
				user.setStatus(i);
				boolean b = Boolean.parseBoolean(item[4]);
				user.setFine(b);
				userArrayList.add(user);

			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void write() {
		try {
			File csv = new File("userList.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			for (int i = 0; i < userArrayList.size(); i++) {
				bw.write(userArrayList.get(i).getStudentID() + "," + userArrayList.get(i).getName() + ","
						+ userArrayList.get(i).getEmail() + "," + userArrayList.get(i).getStatus() + ","
						+ userArrayList.get(i).isFine());
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int searchID(String studentID) {
		for (int i = 0; i < userArrayList.size(); i++) {
			if (userArrayList.get(i).getStudentID().equals(studentID)) {
				return i;
			}
		}
		return 0;
	}

	public static void startUsing(String studentID) {
		int i = searchID(studentID);
		Calendar cld = Calendar.getInstance();
		userArrayList.get(i).setStatus(1);// 1 means using now
		userArrayList.get(i).setCld(cld);
	}

	public static void endUsing(String studentID) {
	
		int[] time = usingTime(studentID);
		if (time[0] > 30 || time[1] > 120) {
			ban(studentID);
		}
	}

	public static int[] usingTime(String studentID) {
		int i = searchID(studentID);
		int[] time = new int[2];
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_YEAR);
		Calendar last = userArrayList.get(i).getCld();
		int minute = (now.get(Calendar.YEAR) - last.get(Calendar.YEAR)) * 525600
				+ (now.get(Calendar.DAY_OF_YEAR) - last.get(Calendar.DAY_OF_YEAR));
		
		userArrayList.get(i).setStatus(2);// 2 means no using

		time[0] = minute;
		time[1] = minute;
		UsageControl.updateUsage(studentID, day, minute);
		return time;
	}

	public static void ban(String studentID) {
		int i = searchID(studentID);
		if (i >= 0) {
			userArrayList.get(i).setFine(true);
			System.out.println("you are fined");
		} else
			System.out.println("something wrong");
	}

	public static boolean isCurrentUsing(String studentID) {
		int i = searchID(studentID);
		if (userArrayList.get(i).getStatus() == 1) {
			return true;
		} else
			return false;
	}

	public static boolean isAbleToBorrow(String studentID) {
		int i = searchID(studentID);
		if (userArrayList.get(i).getStatus() == 2 && userArrayList.get(i).isFine() == false) {
			return true;
		} else
			return false;
	}

	public static void unBan(String studentID) {
		int i = searchID(studentID);
		if (i >= 0) {
			userArrayList.get(i).setFine(false);
		} else
			System.out.println("something wrong");
	}
}
