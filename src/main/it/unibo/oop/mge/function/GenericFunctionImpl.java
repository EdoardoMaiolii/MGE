package it.unibo.oop.mge.function;

class GenericFunctionImpl<X> implements GenericFunction<X> {
    private X type;

    GenericFunctionImpl(final X type) {
        this.type = type;
    }

    public final X getType() {
        return type;
    }
}
