package Model;
import java.util.ArrayList;

public class LivingBeing {
	String name;
	String address;
	BeingType type;
	ArrayList<LivingBeing> dependants;
	
	@Override
	public String toString() {
		return this.name;
	}

	public LivingBeing(String name, String address, BeingType type) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.dependants = new ArrayList<LivingBeing>();
	}
	
	public void addDependant(LivingBeing dependant) {
		this.dependants.add(dependant);
	}
}
