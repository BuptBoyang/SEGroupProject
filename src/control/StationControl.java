package control;
import java.util.ArrayList;

import entity.Station;



public class StationControl {
	private ArrayList<Station> stations = new ArrayList<Station>(); 
	
	
	public void lock(int i,int j) {
		stations.get(i).lock(j);
	}
}
