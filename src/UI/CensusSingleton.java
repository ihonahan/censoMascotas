package UI;
import java.util.ArrayList;
import Model.*;

public class CensusSingleton {
	public static CensusSingleton shared = new CensusSingleton();
	
	private RegionList regions = new RegionList();
	public User user;
	
	private Region selectedRegion;
	private LivingBeing selectedHuman;
	
	private ArrayList<CensusListener> listeners = new ArrayList<CensusListener>();
	
	private CensusSingleton() {
		
	}
	
	public void addListener(CensusListener listener) {
		listeners.add(listener);
	}
	
	
	public boolean userAuthenticated() {
		return this.user != null;
	}
	
	public boolean isRegionSelected() {
		return this.selectedRegion != null;
	}

	public boolean isHumanSelected() {
		return this.selectedHuman!= null;
	}

	
	/// Regions methods
	
	public ArrayList<Region> getAllRegions() {
		return regions.getAllRegions();
	}
	
	public ArrayList<Region> getRegions(String byName) {
		return regions.getRegions(byName);
	}
	
	
	public void addRegion(String regionName) {
		regions.addRegion(regionName);
		
		for (CensusListener listener: listeners) {
			listener.regionAdded();
		}
	}
	
	public void assignSelectedRegion(Region region) {
		selectedRegion = region;

		for (CensusListener listener: listeners) {
			listener.regionSelected();
		}
	}
	
	
	/// Living beings methods
	
	public ArrayList<LivingBeing> getHumansFromSelectedRegion() {
		return selectedRegion.getAllInhabitants();
	}
	
	public ArrayList<LivingBeing> getBeingsByNameInSelectedRegion(String name) {
		return selectedRegion.getAllBeings(name);
	}

	public ArrayList<LivingBeing> getAllHumasFromSelectedRegion() {
		return selectedRegion.getAllHumansFromThisRegion();
	}

	public void addHuman(LivingBeing livingBeing) {
		if (selectedRegion == null) {
			return;
		}
		
		selectedRegion.addHuman(livingBeing);
		
		for (CensusListener listener: listeners) {
			listener.humanAdded();
		}
	}

	public void assignSelectedHuman(LivingBeing livingBeing) {
		selectedHuman = livingBeing;

		for (CensusListener listener: listeners) {
			listener.humanSelected();
		}
	}
	
	public void addPet(LivingBeing livingBeing) {
		if (selectedRegion == null || selectedHuman == null) {
			return;
		}
		
		selectedHuman.addDependant(livingBeing);
		
		for (CensusListener listener: listeners) {
			listener.petAdded();
		}
	}
}
