
package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.Station;

public class StationControl {

	public static boolean success = false;

	public static ArrayList<Station> stationArrayList = new ArrayList<Station>();

	public static void write() {
		try {
			File csv = new File("stationList.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			for (int i = 0; i < stationArrayList.size(); i++) {
				for (int j = 0; j < Station.getSlotAmount(); j++) {
					bw.write(stationArrayList.get(i).getSlot(j).isHasScooter() + ",");
				}
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("stationList.csv"));
//			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				Station station = new Station();
				for (int j = 0; j < Station.getSlotAmount(); j++) {
					boolean b = Boolean.parseBoolean(item[j]);
					station.getSlot(j).setHasScooter(b);
				}
				stationArrayList.add(station);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int findFreeSlot(int index) {
		int slotNo = -1;
		for (int i = 0; i < Station.getSlotAmount(); i++) {
			if (stationArrayList.get(index).getSlot(i).isHasScooter() == false) {
				slotNo = i;
				break;
			}
		}
		return slotNo;
	}

	public static int findFreeScooter(int index) {
		int slotNo = -1;
		for (int i = 0; i < Station.getSlotAmount(); i++) {
			if (stationArrayList.get(index).getSlot(i).isHasScooter() == true) {
				slotNo = i;
				break;
			}
		}
		return slotNo;
	}

	public static int scanCard(String studentID) {
		if (UserControl.isCurrentUsing(studentID) == true) {
			return 1;// want to return a scooter
		} else if (UserControl.isAbleToBorrow(studentID) == true) {
			return 2; // borrow
		} else {
			return 3;// fire
		}
	}

	public static void userTakeScooter(int index) {
		int s = findFreeScooter(index);
		if (s == -1) {
			System.out.print("no spare scooter");
		} else {
			stationArrayList.get(index).getSlot(s).setLocked(false);
		}
	}

	public static void userReturnSccoter(int index) {
		int s = findFreeSlot(index);
		if (s == -1) {
			System.out.print("no spare slot");
		} else {
			stationArrayList.get(index).getSlot(s).setLocked(false);
		}
	}

	public static boolean isSuccess() {
		return success;
	}

	public static void setSuccess(boolean success) {
		StationControl.success = success;
	}

	public static void reSetAfterBorrow(int index, int i) {
			stationArrayList.get(index).getSlot(i).setHasScooter(false);
			stationArrayList.get(index).getSlot(i).setLocked(true);
	}

	public static void reSetAfterReturn(int index, int i) {
			stationArrayList.get(index).getSlot(i).setHasScooter(true);
			stationArrayList.get(index).getSlot(i).setLocked(true);
	}

	public static int scooterNumberInStation(int index) {
		int amount = 0;
		for (int i = 0; i < Station.getSlotAmount(); i++) {
			if (stationArrayList.get(index).getSlot(i).isHasScooter()) {
				amount++;
			}
		}
		return amount;
	}
}
