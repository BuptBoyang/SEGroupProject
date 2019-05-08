
package control;

import entity.Station;

public class StationControl {	
	private final int stationAmount = 3;
	private static Station[] stations = {
			new Station(),
			new Station(),
			new Station()
	};
	
	public static void scanCard(String studentID,int index) {
		
		if(UserControl.isCurrentUsing(studentID)==true) {
			System.out.println("please return");
			userReturnScooter(studentID,index);
		} 
		else if (UserControl.isAbleToBorrow(studentID)==true) {
			System.out.println("please take");
			userTakeScooter(studentID,index);
		}
		else if(UserControl.isAbleToBorrow(studentID)==false) {
			System.out.println("Pay your fine in the office before using a scooter");
		}
	}
	
	private static void userReturnScooter(String studentID,int index) {
		int slotNo = stations[index].findFreeSlot(false);
		stations[index].releaseSlot(slotNo);
		stations[index].pressSimulator(slotNo);
		stations[index].timeout(slotNo);
		if(stations[index].checkToReturn(slotNo)==true) {
			UserControl.takeScooter(studentID);
			stations[index].reset(slotNo,true);
			System.out.println("Return the scooter successfully");
		} else {
			System.out.println("Retrun the scooter unsuccessfully");
		}
	}

	public static void userTakeScooter(String studentID,int index) {
		int slotNo = stations[index].findFreeSlot(true);
		stations[index].releaseSlot(slotNo);
		stations[index].pressSimulator(slotNo);
		stations[index].timeout(slotNo);
		if(stations[index].checkToBorrow(slotNo)==true) {
			UserControl.takeScooter(studentID);
			stations[index].reset(slotNo,false);
			System.out.println("Take the scooter successfully");
		} else {
			System.out.println("Take the scooter unsuccessfully");
		}
	}
	
}
