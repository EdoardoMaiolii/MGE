package model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Variables {
	
	X("x"),
	Y("y"),
	Z("z"),
	A("a"),
	B("b"),
	C("c");
	
	private final String name;
	
	private Variables(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Set<String> names(){
	  return Arrays.asList(Variables.values()).stream()
	                                          .map(e -> e.getName())
	                                          .collect(Collectors.toSet());
	}
}
