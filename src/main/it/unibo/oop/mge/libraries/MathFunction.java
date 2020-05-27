package it.unibo.oop.mge.libraries;

import java.util.List;

public enum MathFunction implements GenericEnum {
    /**
     * make the sum.
     */
    SUM(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) + parameters.get(1);
        }
    },
    /**
     * make the sub.
     */
    SOT(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) - parameters.get(1);
        }
    },
    /**
     * 
     */
    MUL(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) * parameters.get(1);
        }
    },
    /**
     * 
     */
    DIV(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) / parameters.get(1);
        }
    },
    /**
     * 
     */
    POW(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * 
     */
    EXP(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.exp(parameters.get(0));
        }
    },
    /**
     * 
     */
    SQRT(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sqrt(parameters.get(0));
        }
    },
    /**
     * 
     */
    LOG(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(1)) / Math.log(parameters.get(0));
        }
    },
    /**
     * 
     */
    LN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(0));
        }
    },
    /**
     * 
     */
    RTN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(1), 1 / parameters.get(0));
        }
    },
    /**
     * 
     */
    ABS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.abs(parameters.get(0));
        }
    },
    /**
     * 
     */
    SIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sin(parameters.get(0));
        }
    },
    /**
     * 
     */
    COS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cos(parameters.get(0));
        }
    },
    /**
     * 
     */
    ACOS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.acos(parameters.get(0));
        }
    },
    /**
     * 
     */
    ASIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.asin(parameters.get(0));
        }
    },
    /**
     * 
     */
    TAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tan(parameters.get(0));
        }
    },
    /**
     * 
     */
    ATAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.atan(parameters.get(0));
        }
    },
    /**
     * 
     */
    COSH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cosh(parameters.get(0));
        }
    },
    /**
     * 
     */
    SINH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sinh(parameters.get(0));
        }
    },
    /**
     * 
     */
    TANH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tanh(parameters.get(0));
        }
    },
    /**
     * 
     */
    MAX(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.max(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * 
     */
    MIN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.min(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * 
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

    public static List<String> getListFromEnum() {
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
