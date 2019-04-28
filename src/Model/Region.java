package Model;
import java.util.ArrayList;

public class Region {
	String name;
	ArrayList<LivingBeing> inhabitants;
	
	public Region(String name) {
		this.name = name;
		this.inhabitants = new ArrayList<LivingBeing>();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public void addHuman(LivingBeing human) {
		this.inhabitants.add(human);
	}
	
	public ArrayList<LivingBeing> getAllInhabitants() {
		return this.inhabitants;
	}
	
	public ArrayList<LivingBeing> getAllBeings(String withName) {
		ArrayList<LivingBeing> result = getBeingsByNameIn(inhabitants, withName);		
		
		return result;
	}
	
	public ArrayList<LivingBeing> getBeingsByNameIn(ArrayList<LivingBeing> beings, String name) {
		ArrayList<LivingBeing> result = new ArrayList<LivingBeing>();
		
		String lowerName = name.toLowerCase();
		
		for (LivingBeing being: beings) {
			if (being.dependants.size() > 0) {
				result.addAll(getBeingsByNameIn(being.dependants, lowerName));
			}

			if (being.name.toLowerCase().contains(lowerName)) {
				result.add(being);
			}
		}
		
		return result;
	}
	
	public ArrayList<LivingBeing> getAllHumansFromThisRegion() {
		ArrayList<LivingBeing> result = getAllHumansFrom(inhabitants);
		
		return result;
	}
	
	public ArrayList<LivingBeing> getAllHumansFrom(ArrayList<LivingBeing> beings) {
		ArrayList<LivingBeing> result = new ArrayList<LivingBeing>();
		
		for (LivingBeing being: beings) {
			if (being.dependants.size() > 0) {
				result.addAll(getAllHumansFrom(being.dependants));
			}
			
			if (being.type == BeingType.ADULTHUMAN || being.type == BeingType.CHILDHUMAN) {
				result.add(being);
			}
		}
		
		return result;
	}
}
