package boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import control.StationControl;
import control.UserControl;
import entity.Station;
import entity.User;

public class text {

	private static ArrayList<User> userArrayList = new ArrayList<User>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lenovo\\Desktop\\userList.csv"));
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

		for(int i=0;i<userArrayList.size();i++) {
//			System.out.println(userArrayList.get(i));
//			System.out.println(userArrayList.get(i).isFine());
			System.out.println(scanCard(userArrayList.get(i).getStudentID()));
		}
		
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 8; j++) {
//
//				boolean b = stationArrayList.get(i).getSlot(j).isHasScooter();
//
//				System.out.print(b);
//			}
//			System.out.println("");
//		}

	}
	
	public static int searchID(String studentID) {
		for (int i = 0; i < userArrayList.size(); i++) {
			if (userArrayList.get(i).getStudentID().equals(studentID)) {
				return i;
			}
		}
		return 0;
	}
	
	public static int scanCard(String studentID) {
		if (isCurrentUsing(studentID) == true) {
			return 1;// want to return a scooter
		} else if (isAbleToBorrow(studentID) == true) {
			return 2; // borrow
		} else {
			return 3;// fire
		}
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

}
