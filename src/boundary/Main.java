package boundary;

import control.StationControl;
import control.UserControl;
import entity.User;

public class Main {
	public static void main(String args[]){
		UserControl.read();
		User user = new User("161188955","leeming","jp2016213590@qmul.ac.uk");
		if(!UserControl.isDuplication("161188955"))
			UserControl.register(user);
		
		System.out.println("enter id");
		StationControl.scanCard("161188955",0);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StationControl.scanCard("161188955",0);
	}

}
