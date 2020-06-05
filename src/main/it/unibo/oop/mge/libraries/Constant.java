package it.unibo.oop.mge.libraries;

public enum Constant implements GenericEnum {
    /**
     * Nepero's number.
     */
    E(2.7182),
    /**
     * Pi greek.
     */
    PI(3.1415),
    /**
     * Golden ratio.
     */
    PHI(1.6180),
    /**
     * Euler-Mascheroni's number.
     */
    EUMA(0.5772),
    /**
     * Embree-Trefethen's number.
     */
    EMTR(0.7025),
    /**
     * Plastic number.
     */
    PLS(1.3247),
    /**
     * 'Feigenbaum first' number.
     */
    FEIGPR(4.6692),
    /**
     * 'Feigenbaum second' number.
     */
    FEIGSN(2.5029),
    /**
     * Primes twins ratio.
     */
    PRTW(0.6601),
    /**
     * Mills number.
     */
    MILLS(1.3063),
    /**
     * Brun's constant for twin primes.
     */
    BRUNTW(1.9021),
    /**
     * Brun's constant for prime quadruplets.
     */
    BRUMQUAD(0.8705);

    private final Double value;

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
