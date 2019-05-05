package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.User;

public class UserControl {

	private ArrayList<User> userArrayList = new ArrayList<User>();

	public boolean isDuplication(String id) {
		for (int i = 0; i < userArrayList.size(); i++) {

			if (userArrayList.get(i).getStudentID().equals(id) ) {
				return false;
			}
		}
		return true;
	}

	public void register(User user) {
		userArrayList.add(user);
		System.out.println(user);
	}
	
	//getter method for userArrayList.
	public ArrayList<User> getUserArrayList() {
		return userArrayList;
	}
	
	public User findID(String id) {
		User user = null;
		return user;
	}

/*	public void read() {
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
	}*/
}