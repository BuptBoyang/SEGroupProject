package entity;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class Slot {
	private boolean hasScooter;
	private boolean isLocked;
	private JLabel light;
	private JButton button;
	boolean success;//是否成功关锁，在actionPerform里置为true
	
	public Slot(boolean hasScooter,int index) {
		this.hasScooter = hasScooter;
		this.isLocked = true;
		this.light = new JLabel();
		this.button = new JButton(""+index);
		light.setOpaque(true);
		light.setBackground(Color.WHITE);
	}
	
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
