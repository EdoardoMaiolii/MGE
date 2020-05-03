package libraries;

import java.util.List;

public class NDPoint {
	private List<Double> values;
	public NDPoint(List<Double> values) {
		this.values = values;
	}
	
	public List<Double> getCoordinates() {
		return values;
	}
	
	public String toString() {
		return values.toString()+"\n";
	}
}
