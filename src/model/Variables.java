package view;
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
	
}
