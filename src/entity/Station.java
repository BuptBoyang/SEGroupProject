package entity;

import java.io.Serializable;

public class Station implements Serializable{
	private Slot slot[];
	private final int slotAmount = 8;
	private boolean isTimeout = false;

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
	
	public Slot getSlot(int i) {
		return slot[i];
	}

	public int getSlotAmount() {
		return slotAmount;
	}
	
	
	public void pressSimulator(int slotNo) {
		slot[slotNo].isUserActionDone=true;
	}
	
	public void openSlot(int slotNo) {
		slot[slotNo].setLocked(false);
	}
	
	public void closeSlot(int slotNo) {
		slot[slotNo].setLocked(true);
	}
	
	public boolean checkToBorrow(int slotNo) {
		boolean borrowResult = false;
		while(isTimeout==false) {
			if(slot[slotNo].isUserActionDone==true) {
				borrowResult = true;
				break;
			}
		}
		isTimeout = false;
		return borrowResult;
	}
	
	public boolean checkToReturn(int slotNo) {
		boolean returnResult = false;
		while(isTimeout==false) {
			if(slot[slotNo].isUserActionDone==true) {
				returnResult = true;
				break;
			}
		}
		isTimeout = false;
		return returnResult;
	}
	
	public void reset(int slotNo,boolean hasScooter) {
		slot[slotNo].isUserActionDone = false;
		slot[slotNo].setHasScooter(hasScooter);
		closeSlot(slotNo);
	}
	
	public void timeout(int slotNo) {
		final long time = 60000;
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(slot[slotNo].isUserActionDone==false && slot[slotNo].isLocked()==false) {
					System.out.println("Timeout");
					slot[slotNo].setLocked(true);
					isTimeout = true;
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public void releaseSlot(int slotNo) {
		openSlot(slotNo);
		//slot[slotNo].flashLight();
	}
	
}
