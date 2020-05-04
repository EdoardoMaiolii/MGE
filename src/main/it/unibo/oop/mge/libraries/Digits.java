package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Digits {
	
	ZERO("0"),
	ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");
	
	private final String name;
	
	private Digits(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected static List<String> getListFromEnum() {
	    return Arrays.asList(Digits.values()).stream()
                                              .map(e -> e.getName())
                                              .collect(Collectors.toList());
    }
}
