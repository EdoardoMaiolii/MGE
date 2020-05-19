package it.unibo.oop.mge.function;
public class GenericFunctionImpl<X> implements GenericFunction<X> {
	private X type ; 
	protected GenericFunctionImpl(X type) {
		this.type = type;
	}
	
	public X getType() {
		return type;
	}
}
