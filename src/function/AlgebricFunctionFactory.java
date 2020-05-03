package function;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import libraries.Constants;
import libraries.MathFunctions;

public interface AlgebricFunctionFactory {
	/**
	 * 
	 * @param value is the Number
	 * @return an AlgebricFunction that correspond to a Number
	 */
	public static AlgebricFunctionImpl<Double> getValueFunction(Double value) {
		return new AlgebricFunctionImpl<Double>(value,Optional.empty()){
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return this.getType();
			}
		};
	}
	/**
	 * 
	 * @param c is the constant 
	 * @return an AlgebricFunction that correspond to the value of the constant
	 */
	public static AlgebricFunctionImpl<Double> getConstantFunction(Constants c) {
		return getValueFunction(c.resolve());
	}
	/**
	 * 
	 * @param name is the name if the variable
	 * @return an AlgebricFunction that correspond to a variable
	 */
	public static AlgebricFunctionImpl<Character> getParameterFunction(Character name) {
		return new AlgebricFunctionImpl<Character>(name,Optional.empty()){
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return values.get(pars.indexOf(this.getType()));
			}
		};
	}
	/**
	 * 
	 * @param id is the type of the Function
	 * @param pars is the parameters of the Function
	 * @return an AlgebricFunction that is a Mathematical Function
	 */
	public static AlgebricFunctionImpl<MathFunctions> getMathFunction(MathFunctions id, List<AlgebricFunctionImpl<?>> pars) {
		return new AlgebricFunctionImpl<MathFunctions>(id,Optional.of(pars)) {
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return this.getType().resolve(this.getParameters().get().stream().map(i -> i.resolve(pars, values)).collect(Collectors.toList()));
			}
		};
	}
}
