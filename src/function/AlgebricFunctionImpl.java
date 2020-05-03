package function;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import libraries.MathFunctions;

public abstract class AlgebricFunctionImpl<X> extends GenericFunctionImpl<X> implements AlgebricFunction<X>{
	
	enum Types{
		CONSTANT,
		VARIABLE,
		MATHFUNCTION;
	}
	
	private Map<Class<?>,Types> enummap = new HashMap<>();
	private Optional<List<AlgebricFunctionImpl<?>>> myParameters;
	
	protected AlgebricFunctionImpl(X info , Optional<List<AlgebricFunctionImpl<?>>> parameters) {
		super(info);
		enummap.put(Double.class,Types.CONSTANT);
		enummap.put(Character.class,Types.VARIABLE);
		enummap.put(MathFunctions.class,Types.MATHFUNCTION);
		myParameters = parameters;
	}
	
	public Boolean isVariable() {
		return this.getType() instanceof Character; 
	}
	
	public Boolean isConstant() {
		return this.getType() instanceof Double; 
	}
	
	public Boolean isMathFunction() {
		return this.getType() instanceof MathFunctions;
	}	
	
	abstract public Double resolve(List<Character> pars , List<Double> values);
	
	public Optional<List<AlgebricFunctionImpl<?>>> getParameters() {
		return myParameters;
	}
}
