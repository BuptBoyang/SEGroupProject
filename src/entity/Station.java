package entity;

import javax.swing.*;;

public class Station {
	private Slot slot[];
	private JPanel stationPanel;
	User currentUser;
	
	public int findFreeSlot() {
		int slotNo=0;
		return slotNo;
	}
	
	public void openSlot() {
		
	}
	
	public void checkUserState() {
		
	}
	
	public void checkToBorrow() {
		
	}
	
	public boolean checkToReturn(int slotNo) {
		boolean returnResult = false;
		if(currentUser.getStatus()==1) {
			while(slot[slotNo].success==false);
			if(slot[slotNo].isHasScooter()) {
				System.out.println("Successfully return a scooter");
				returnResult = true;
				//calculate usage
			} else {
				System.out.println("Unsuccessfully return a scooter");
			}
		} else {
			System.out.println("You are not able to return a scooter.");
		}
		return returnResult;
	}
	
	public void timeoutUnborrow() {
		
	}
	
	public void timeoutUnreturn() {
		
	}
	
	public void pickScooter() {
		
	}
	
	public void returnScooter() {
		
	}
}
