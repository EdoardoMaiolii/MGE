package it.unibo.oop.mge.libraries;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MathFunctions implements MathEnum {
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
    };

    private final int nParameters;

    MathFunctions(final int nPar) {
        this.nParameters = nPar;
    }

    public static List<String> getListFromEnum() {
        return Arrays.asList(values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList());
    }

    public static boolean contains(final String name) {
        return  getListFromEnum().contains(name);
    }
    public static MathFunctions getMathFunctionFromSyntax(final String syntax) {
        return MathFunctions.valueOf(Arrays.asList(values())
               .stream()
               .map(i -> new Pair<String, String>(i.name(), i.getSyntax()))
               .filter(i -> i.getSnd().equals(syntax))
               .findFirst()
               .get()
               .getFst());
    }
    public int getNParameters() {
        return nParameters;
    }

    public String getSyntax() {
        return this.name().toLowerCase();
    }

    public Double resolve(final List<Double> list) {
        return calculate(list);
    }

    protected abstract Double calculate(List<Double> parameters);
}
