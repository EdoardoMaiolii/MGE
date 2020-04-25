package view;
public enum Settings {
  
	MAX("Max"),
	MIN("Min"),
	RATE("Rate");
	
	private final String name;
	
	private Settings(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
