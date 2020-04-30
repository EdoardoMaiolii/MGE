package functions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import libraries.*;

public class AlgebricFunctionFactory {
	
	private AlgebricFunctionFactory() {}
	
	public static AlgebricFunction<Double> getValueFunction(Double value) {
		return new AlgebricFunction<Double>(value,Optional.empty()){
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return this.getInfo();
			}
		};
	}
	public static AlgebricFunction<Double> getConstantFunction(Constants c) {
		return getValueFunction(c.getValue());
	}
	public static AlgebricFunction<Character> getParameterFunction(Character name) {
		return new AlgebricFunction<Character>(name,Optional.empty()){
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return values.get(pars.indexOf(this.getInfo()));
			}
		};
	}
	public static AlgebricFunction<MathFunctions> getMathFunction(MathFunctions id, List<AlgebricFunction<?>> pars) {
		return new AlgebricFunction<MathFunctions>(id,Optional.of(pars)) {
			@Override
			public Double resolve(List<Character> pars, List<Double> values) {
				return this.getInfo().resolve(this.getParameters().get().stream().map(i -> i.resolve(pars, values)).collect(Collectors.toList()));
			}
		};
	}
}
