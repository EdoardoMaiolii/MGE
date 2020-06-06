package it.unibo.oop.mge.libraries;

import java.util.List;

/**
 * The Interface EnumUtility.
 *
 * @param <X> the generic type
 */
public interface EnumUtility<X extends GenericEnum> {

    /**
     * Gets the syntax list.
     *
     * @return a list of the syntax of all the elements of the enum.
     */
    List<String> getSyntaxList();

    /**
     * Gets the element corresponding to the given syntax.
     *
     * @param syntax the syntax
     * @return the element corresponding to the given syntax.
     */
    X getElement(String syntax);

    /**
     * Check if the enum contains a a element that correspond to the given syntax.
     *
     * @param syntax the syntax
     * @return true if the enum contains a element that correspond to the given
     *         syntax, false otherwise.
     */
    Boolean enumContains(String syntax);
}
