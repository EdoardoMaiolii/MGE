package model;
public enum Punctuation {
	
	DOT("."),
	COMMA(","),
	OPEN_PARENTHESES("("),
	CLOSE_PARENTHESES(")");
	
	private final String name;
	
	private Punctuation(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return this.name;
	}
}
