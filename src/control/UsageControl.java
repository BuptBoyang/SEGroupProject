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

import entity.Usage;

public class UsageControl {
	public static ArrayList<Usage> usageArrayList = new ArrayList<Usage>();

	public static void updateUsage(String studentID, int date, int time) {
		for (int i = 0; i < usageArrayList.size(); i++) {
			if (usageArrayList.get(i).getStudentID().equals(studentID)) {
				if (usageArrayList.get(i).getDate() == date) {
					usageArrayList.get(i).setDayUsage(time);
				} else {
					Usage usage = new Usage(studentID, date, time);
					usageArrayList.add(usage);
				}
			}
		}
	}

	public static void removeUsage() {
		Calendar now = Calendar.getInstance();
		int today = now.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < usageArrayList.size(); i++) {
			if (today - usageArrayList.get(i).getDate() > 7) {
				usageArrayList.remove(i);
			}
		}
	}

	public static int searchID(String studentID) {
		for (int i = 0; i < usageArrayList.size(); i++) {
			if (usageArrayList.get(i).getStudentID().equals(studentID)) {
				return i;
			}
		}
		return -1;
	}

	public static void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lenovo\\Desktop\\usage.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				Usage usage = new Usage(item[0], Integer.parseInt(item[1]), Integer.parseInt(item[2]));
				usageArrayList.add(usage);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void write(Usage usage) {
		try {
			File csv = new File("C:\\Users\\lenovo\\Desktop\\usage.csv");

			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			bw.write(usage.getStudentID() + "," + usage.getDate() + "," + usage.getDayUsage());
			bw.newLine();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
