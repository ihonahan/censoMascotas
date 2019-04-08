package Model;
import java.util.ArrayList;
import java.util.Iterator;

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
	
	public ArrayList<Region> getRegions(String name) {
		ArrayList<Region> result = new ArrayList<Region>();
		
		for(int index = 0; index < this.regions.size(); index++) {
			Region item = this.regions.get(index);
			if(item.name.contains(name)) {
				result.add(item);
			}
		}
		
		return result;
	}
}
