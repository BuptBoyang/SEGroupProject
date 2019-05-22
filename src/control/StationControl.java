
package control;

import entity.Station;

public class StationControl {	
	private static Station[] stations = {
			new Station(),
			new Station(),
			new Station()
	};
	
	public static String scanCard(String studentID,int index) {
		String message = new String();
		if(UserControl.isCurrentUsing(studentID)==true) {
			message += "please return\n";
			message += userReturnScooter(studentID,index);
		} 
		else if (UserControl.isAbleToBorrow(studentID)==true) {
			message += "please take\n";
			message += userTakeScooter(studentID,index);
		}
		else if(UserControl.isAbleToBorrow(studentID)==false) {
			message += "Pay your fine in the office before using a scooter";
		}
		
		return message;
	}

	private static String userReturnScooter(String studentID,int index) {
		int slotNo = stations[index].findFreeSlot(false);
		if(slotNo==-1) {
			return "No free slot";
		}
		String message = new String();
		stations[index].releaseSlot(slotNo);
		stations[index].pressSimulator(slotNo);
		stations[index].timeout(slotNo);
		if(stations[index].checkToReturn(slotNo)==true) {
			UserControl.endUsing(studentID);
			stations[index].reset(slotNo,true);
			message = "Return the scooter successfully";
		} else {
			message = "Retrun the scooter unsuccessfully";
		}
		return message;
	}

	public static String userTakeScooter(String studentID,int index) {
		int slotNo = stations[index].findFreeSlot(true);
		if(slotNo==-1) {
			return "No free slot";
		}
		String message = new String();
		stations[index].releaseSlot(slotNo);
		stations[index].pressSimulator(slotNo);
		stations[index].timeout(slotNo);
		if(stations[index].checkToBorrow(slotNo)==true) {
			UserControl.startUsing(studentID);
			stations[index].reset(slotNo,false);
			message = "Take the scooter successfully";
		} else {
			message = "Take the scooter unsuccessfully";
		}
		return message;
	}
	
}
