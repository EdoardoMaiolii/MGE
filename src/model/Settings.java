package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<String> names(){
      return Arrays.asList(Settings.values()).stream()
                                              .map(e -> e.getName())
                                              .collect(Collectors.toList());
    }
}
