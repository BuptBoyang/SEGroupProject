
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Station;

public class StationControl {	

	private static Station[] stations = {
			new Station(),
			new Station(),
			new Station()
	};
	
	public static void saveStation() {
		ObjectOutputStream oos = null;  
        FileOutputStream fos = null;
		try {
			File f = new File("station");
			fos = new FileOutputStream(f);  
            oos = new ObjectOutputStream(fos);  
            oos.writeObject(stations);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
            if (oos != null) {  
                try {  
                    oos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e2) {  
                    e2.printStackTrace();  
                }  
            }  
        }  
	}
	
	public static void read(){
		FileInputStream fis = null;  
        ObjectInputStream ois = null; 
		try {
			fis = new FileInputStream("station");  
            ois = new ObjectInputStream(fis);
            stations =  (Station[]) ois.readObject();  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
            if (fis != null) {  
                try {  
                    fis.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (ois != null) {  
                try {  
                    ois.close();  
                } catch (IOException e2) {  
                    e2.printStackTrace();  
                }  
            }  
        }  
	}
	
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
			stations[index].reset(slotNo,false);
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
			stations[index].reset(slotNo,true);
			message = "Take the scooter unsuccessfully";
		}
		return message;
	}
	
	public static int scooterNumberInStation(int index) {
		int amount = 0;
			for(int i=0;i<stations[index].getSlotAmount();i++) {
				if(stations[index].getSlot(i).isHasScooter()) {
					amount++;
				}
			}
		return amount;
	}
	
}
