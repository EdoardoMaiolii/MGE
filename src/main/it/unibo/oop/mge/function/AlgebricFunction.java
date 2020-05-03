package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

public interface AlgebricFunction<X> extends GenericFunction<X> { 
	enum Types{
		CONSTANT,
		VARIABLE,
		MATHFUNCTION;
	}
	/**
	 * 
	 * @return true if the function is a Variable Function
	 */
	public Boolean isVariable();
	/**
	 * 
	 * @return true if the function is a Mathematical Constant Function
	 */
	public Boolean isConstant();
	/**
	 * 
	 * @return true if the function is a Mathematical Function
	 */
	public Boolean isMathFunction();
	/**
	 * 
	 * @param pars are the names of the variables
	 * @param values are the values if the variables 
	 * @return the value of the function
	 */
	abstract public Double resolve(List<Character> pars , List<Double> values);
	/**
	 * 
	 * @return the parameters
	 */
	public Optional<List<AlgebricFunctionImpl<?>>> getParameters();
}
