package functions;
public class GenericFunction<X>{
	private X myType ; 
	protected GenericFunction(X type) {
		myType = type;
	}
	
	public X getInfo() {
		return myType;
	}
}
