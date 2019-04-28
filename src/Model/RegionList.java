package Model;
import java.util.ArrayList;
import java.util.Iterator;

public class RegionList {
	ArrayList<Region> regions;
	
	public RegionList() {
		this.regions = new ArrayList<Region>(); 
	}
	
	public boolean addRegion(String name) {
		Region region = new Region(name);
		
		this.regions.add(region);
		
		return true;
	}
	
	public Region getRegion(int atIndex) {
		return this.regions.get(atIndex);
	}
	
	public ArrayList<Region> getRegions(String name) {
		ArrayList<Region> result = new ArrayList<Region>();
		
		String lowerName = name.toLowerCase();
		
		for(int index = 0; index < this.regions.size(); index++) {
			Region item = this.regions.get(index);
			if(item.name.toLowerCase().contains(lowerName)) {
				result.add(item);
			}
		}
		
		return result;
	}
	
	public ArrayList<Region> getAllRegions() {
		return this.regions;
	}
}
