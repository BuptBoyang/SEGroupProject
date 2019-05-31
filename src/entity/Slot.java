package entity;

public class Slot {
	private boolean hasScooter;
	private boolean isLocked;
	
	public Slot(boolean hasScooter,int index) {
		this.hasScooter = hasScooter;
		this.isLocked = true;
	}
	
	public boolean isHasScooter() {
		return hasScooter;
	}
	public void setHasScooter(boolean hasScooter) {
		this.hasScooter = hasScooter;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
}
