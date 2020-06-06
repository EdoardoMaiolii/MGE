package it.unibo.oop.mge.libraries;

import java.util.List;

public interface EnumUtility<X extends GenericEnum> {
    /**
     * 
     * @return a list of the syntax of all the elements of the enum.
     */
    List<String> getSyntaxList();

    /**
     * 
     * @param syntax
     * @return the element corresponding to the given syntax.
     */
    X getElement(String syntax);

    /**
     * 
     * @param syntax
     * @return true if the enum contains a element that correspond to the given
     *         syntax, false otherwise.
     */
    Boolean enumContains(String syntax);
}
