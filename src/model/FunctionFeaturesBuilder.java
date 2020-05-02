import java.util.List;
import java.util.Optional;

import functions.*;
import libraries.*;

public class FunctionFeaturesBuilder {
	private Optional<AlgebricFunction<?>> function;
	private Optional<List<Pair<Double,Double>>> intervals;
	private Optional<Double> rate;
	
	private Boolean ready() {
		return function.isPresent() && intervals.isPresent() && rate.isPresent();
	}
	private void printError() {
		System.out.println("Error using FunctionFeaturesBuilder");
	}
	
	public FunctionFeaturesBuilder setFunction(AlgebricFunction<?> function) {
			this.function = Optional.of(function);
			return this;
	}
	public FunctionFeaturesBuilder setIntervals(List<Pair<Double,Double>> intervals) {
			this.intervals = Optional.of(intervals);
			return this;
	}
	public FunctionFeaturesBuilder setRate(Double rate) {
			this.rate = Optional.of(rate);
			return this;	
	}
	public FunctionFeatures build() {
		if (ready())
			return new FunctionFeatures(function.get(),intervals.get(),rate.get());
		else {
			printError();
			return null;
		}
	}
}
