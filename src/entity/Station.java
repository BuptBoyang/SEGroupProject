package entity;

import javax.swing.*;;

public class Station {
	private Slot slot[];
	private final int slotAmount = 8;
	private JPanel stationPanel;
	User currentUser;
	
	public Station() {
		this.slot = new Slot[slotAmount];
		for(int i=0;i<slotAmount;i++) {
			if(i<slotAmount/2) {
				slot[i] = new Slot(true,i);
			} else {
				slot[i] = new Slot(false,i);
			}
		}
	}
	
	public int findFreeSlot() {
		int slotNo = -1;
		boolean target = false;
		if(currentUser.getStatus()==0)
			target = true;
		else if(currentUser.getStatus()==1)
			target = false;
		for(int i=0;i<slotAmount;i++) {
			if(slot[i].isHasScooter()==target) {
				slotNo = i;
				break;
			}
		}
		return slotNo;
	}
	
	public void openSlot() {
		
	}
	
	public void checkUserState() {
		
	}
	public void showSlotPosition() {
		JLabel pos = new JLabel();
		int slotNo = findFreeSlot();
		pos.setText(slotNo + " is able to use");
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
