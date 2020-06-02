package it.unibo.oop.mge.libraries;

import java.util.List;

public enum MathFunction implements GenericEnum {
    /**
     * Sum two numbers.
     */
    SUM(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) + parameters.get(1);
        }
    },
    /**
     * Subtract two numbers.
     */
    SOT(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) - parameters.get(1);
        }
    },
    /**
     * Multiply two numbers.
     */
    MUL(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) * parameters.get(1);
        }
    },
    /**
     * Divide two numbers.
     */
    DIV(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) / parameters.get(1);
        }
    },
    /**
     * Raise a base to a exponent.
     */
    POW(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Raise the 'e' to a exponent.
     */
    EXP(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.exp(parameters.get(0));
        }
    },
    /**
     * Square root.
     */
    SQRT(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sqrt(parameters.get(0));
        }
    },
    /**
     * Logarithm with base.
     */
    LOG(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(1)) / Math.log(parameters.get(0));
        }
    },
    /**
     * Logarithm with base 'e'.
     */
    LN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(0));
        }
    },
    /**
     * Nth root.
     */
    RTN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(1), 1 / parameters.get(0));
        }
    },
    /**
     * Absolute value.
     */
    ABS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.abs(parameters.get(0));
        }
    },
    /**
     * Sine function.
     */
    SIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sin(parameters.get(0));
        }
    },
    /**
     * Cosine function.
     */
    COS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cos(parameters.get(0));
        }
    },
    /**
     * Arc cosine function.
     */
    ACOS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.acos(parameters.get(0));
        }
    },
    /**
     * Arc sine function.
     */
    ASIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.asin(parameters.get(0));
        }
    },
    /**
     * Tangent function.
     */
    TAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tan(parameters.get(0));
        }
    },
    /**
     * Arc Tangent function.
     */
    ATAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.atan(parameters.get(0));
        }
    },
    /**
     * Hyperbolic cosine.
     */
    COSH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cosh(parameters.get(0));
        }
    },
    /**
     * Hyperbolic sine.
     */
    SINH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sinh(parameters.get(0));
        }
    },
    /**
     * Hyperbolic tangent.
     */
    TANH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tanh(parameters.get(0));
        }
    },
    /**
     * Maximum of two numbers.
     */
    MAX(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.max(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Minimum of two numbers.
     */
    MIN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.min(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Sign function : return 1 if the value is positive, 
     * return 0 if the value is zero, 
     * return -1 if the value is negative.
     */
    SIGN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) > 0 ? 1.0 : parameters.get(0) < 0 ? -1.0 : 0;
        }
    };

    private final int nParameters;

    MathFunction(final int nPar) {
        this.nParameters = nPar;
    }

    private void throwIllArgExc() {
        throw new IllegalArgumentException("Error using MathFunction");
    }

    public static List<String> getSyntaxList() {
        return EnumUtilityImpl.getSyntaxList(MathFunction.class);
    }

    public static MathFunction getMathFunctionFromSyntax(final String syntax) {
        return (MathFunction) EnumUtilityImpl.getElement(MathFunction.class, syntax);
    }

    public int getNParameters() {
        return nParameters;
    }

    public String getSyntax() {
        return this.name().toLowerCase();
    }

    public Double resolve(final List<Double> list) {
        if (list.size() != nParameters) {
            this.throwIllArgExc();
            return null;
        } else {
            return calculate(list);
        }
    }

    protected abstract Double calculate(List<Double> parameters);
}
