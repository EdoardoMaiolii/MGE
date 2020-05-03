package libraries;
import java.util.Arrays;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Constants implements MathEnum {
    /**
     * nepero's number.
     */
    E(2.7182),
    /**
     * pi greek.
     */
    PI(3.1415),
    /**
     * golden ratio.
     */
    PHI(1.6180),
    /**
     * euler-mascheroni.
     */
    EUMA(0.5772), 
    /**
     * Embree-Trefethen.
     */
    EMTR(0.7025), 
    /**
     *  plastic number.
     */
    PLS(1.3247),
    /**
     * Feigenbaum first.
     */
    FEIGPR(4.6692),
    /**
     * Feigenbaum secondo second.
     */
    FEIGSN(2.5029),
    /**
     *  primes twins.
     */
    PRGEM(0.6601),
    /**
     * mills number.
     */
    MILLS(1.3063),
    /**
     * brim twins.
     */
    BRUNGEM(1.9021),
    /**
     * brum quad.
     */
    BRUMQUAD(0.8705);

    private final Double value;

    private static List<String> getListFromEnum(final Function<Constants, String> function) {
        return Arrays.asList(values()).stream().map(i -> function.apply(i)).collect(Collectors.toList());
    }

    public static List<String> names() {
        return getListFromEnum(i -> i.getLabel());
    }

    public static boolean contains(final String name) {
        return  getListFromEnum(i -> i.getSyntax()).contains(name);
    }

    Constants(final Double value) {
        this.value = value;
    }

    public Double resolve() {
        return value;
    }

    @Override
    public String getSyntax() {
        return getLabel();
    }

    @Override
    public String getLabel() {
        return getSyntax().toLowerCase();
    }
};
