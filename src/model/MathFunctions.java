package libraries;
import java.util.List;

public enum MathFunctions {
	SUM(2,"+") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return parameters.get(0) + parameters.get(1);
		}
	},
	SOT(2,"-") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return parameters.get(0) - parameters.get(1);
		}
	},
	MUL(2,"*") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return parameters.get(0) * parameters.get(1);
		}
	},
	DIV(2,"/") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return parameters.get(0) / parameters.get(1);
		}
	},
	POW(2,"pow") { //base-exponent
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.pow(parameters.get(0), parameters.get(1));
		}
	},
	EXP(1,"exp") { //base-exponent
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.exp(parameters.get(0));
		}
	},
	SQRT(1,"sqrt") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.sqrt(parameters.get(0));
		}
	},
	LOG(2,"log") { //base-argument
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.log(parameters.get(1))/Math.log(parameters.get(0));
		}
	},
	LN(1,"ln") { //base-argument
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.log(Math.log(parameters.get(0)));
		}
	},
	RTN(2,"rtn") { //index-argument
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.pow(parameters.get(1), 1/parameters.get(0));
		}
	},
	ABS(1,"abs") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.abs(parameters.get(0));
		}
	},
	SIN(1,"sin") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.sin(parameters.get(0));
		}
	},
	COS(1,"cos") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.cos(parameters.get(0));
		}
	},
	ACOS(1,"acos") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.acos(parameters.get(0));
		}
	},
	ASIN(1,"asin") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.asin(parameters.get(0));
		}
	},
	TAN(1,"tan") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.tan(parameters.get(0));
		}
	},
	ATAN(1,"atan") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.atan(parameters.get(0));
		}
	},
	COSH(1,"cosh") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.cosh(parameters.get(0));
		}
	},
	SINH(1,"sinh") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.sinh(parameters.get(0));
		}
	},
	TANH(1,"tanh") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.tanh(parameters.get(0));
		}
	},
	MAX(2,"max") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.max(parameters.get(0),parameters.get(1));
		}
	},
	MIN(2,"min") {
		@Override
		protected Double calculate(List<Double> parameters) {
			return Math.min(parameters.get(0),parameters.get(1));
		}
	};
	
	private final int nParameters;
	private final String mylabel;
	
	MathFunctions(int nPar , String label) {
		this.nParameters = nPar;
		this.mylabel = label;
	}
	
	public int getNParameters() {
		return nParameters;
	}
	
	public String getSyntax() {
		return this.name().toLowerCase();
	}
	
	public String getLabel() {
		return mylabel;
	}
	public Double resolve(List<Double> list) {
		if (list.size() == nParameters)
			return calculate(list);
		else 
			return null;
	}
	
	protected abstract Double calculate(List<Double> parameters);
}