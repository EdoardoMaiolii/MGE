package it.unibo.oop.mge.model;
import java.util.Optional;

import it.unibo.oop.mge.function.*;
import it.unibo.oop.mge.libraries.*;
public class FunctionFeaturesBuilder {
	private Optional<AlgebricFunctionImpl<?>> function;
	private Optional<Pair<Double,Double>> interval;
	private Optional<Double> rate;
	
	private Boolean ready() {
		return function.isPresent() && interval.isPresent() && rate.isPresent();
	}
	private void printError() {
		System.out.println("Error using FunctionFeaturesBuilder");
	}
	
	public FunctionFeaturesBuilder setFunction(AlgebricFunctionImpl<?> function) {
			this.function = Optional.of(function);
			return this;
	}
	public FunctionFeaturesBuilder setIntervals(Pair<Double,Double> interval) {
			this.interval = Optional.of(interval);
			return this;
	}
	public FunctionFeaturesBuilder setRate(Double rate) {
			this.rate = Optional.of(rate);
			return this;	
	}
	public FunctionFeaturesImpl build() {
		if (ready())
			return new FunctionFeaturesImpl(function.get(),interval.get(),rate.get());
		else {
			printError();
			return null;
		}
	}
}
