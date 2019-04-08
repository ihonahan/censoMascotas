package Model;
import java.util.ArrayList;

public class RegionList {
	ArrayList<Region> regions;
	
	public boolean addRegion(String name) {
		Region region = new Region(name);
		
		this.regions.add(region);
		
		return true;
	}
	
	public Region getRegion(int atIndex) {
		return this.regions.get(atIndex);
	}
}
