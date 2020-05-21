package it.unibo.oop.mge.libraries;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Constant {
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
     * plastic number.
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
     * primes twins.
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

    public static List<String> getListFromEnum() {
        return EnumSet.allOf(Constant.class).stream().map(i -> i.getSyntax()).collect(Collectors.toList());
    }

    public static Optional<Constant> getConstantFromSyntax(final String syntax) {
        return getListFromEnum().contains(syntax) ? Optional.of(Constant.valueOf(syntax)) : Optional.empty();
    }

    Constant(final Double value) {
        this.value = value;
    }

    public Double resolve() {
        return value;
    }

    public String getSyntax() {
        return this.name().toLowerCase();
    }
};
