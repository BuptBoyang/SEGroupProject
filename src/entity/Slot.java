package entity;

import javax.swing.*;
import java.awt.event.*;

public class Slot {
	private boolean hasScooter;
	private boolean isLocked;
	private JLabel light;
	private JButton button;
	boolean success;//�Ƿ�ɹ���������actionPerform����Ϊtrue
	
	public boolean open() {
		this.success=false;
		return success;
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
