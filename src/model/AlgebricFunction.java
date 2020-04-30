package functions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import libraries.MathFunctions;

public abstract class AlgebricFunction<X> extends GenericFunction<X>{
	
	enum Types{
		CONSTANT,
		VARIABLE,
		MATHFUNCTION;
	}
	
	private Map<Class<?>,Types> enummap = new HashMap<>();
	private Optional<List<AlgebricFunction<?>>> myParameters;
	
	protected AlgebricFunction(X info , Optional<List<AlgebricFunction<?>>> parameters) {
		super(info);
		enummap.put(Double.class,Types.CONSTANT);
		enummap.put(Character.class,Types.VARIABLE);
		enummap.put(MathFunctions.class,Types.MATHFUNCTION);
		myParameters = parameters;
	}
	
	public Boolean isVariable() {
		return this.getInfo() instanceof Character; 
	}
	
	public Boolean isConstant() {
		return this.getInfo() instanceof Double; 
	}
	
	public Boolean isMathFunction() {
		return this.getInfo() instanceof MathFunctions;
	}	
	
	abstract public Double resolve(List<Character> pars , List<Double> values);
	
	public Optional<List<AlgebricFunction<?>>> getParameters() {
		return myParameters;
	}
}
