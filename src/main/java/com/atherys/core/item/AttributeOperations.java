package com.atherys.core.item;

public final class AttributeOperations {

    /**
     * Adds all of the modifiers' amounts to the current value of the attribute.
     * For example, modifying an attribute with {Amount:2,Operation:0} and {Amount:4,Operation:0} with a Base of 3 results in 9 (3 + 2 + 4 = 9).
     */
    public static final int ADDITIVE = 0;

    /**
     * Multiplies the current value of the attribute by (1 + x), where x is the sum of the modifiers' amounts.
     * For example, modifying an attribute with {Amount:2,Operation:1} and {Amount:4,Operation:1} with a Base of 3 results in 21 (3 * (1 + 2 + 4) = 21).
     */
    public static final int MULTIPLICATIVE_ADDITIVE = 1;

    /**
     * For every modifier, multiplies the current value of the attribute by (1 + x), where x is the amount of the particular modifier.
     * Functions the same as Operation 1 if there is only a single modifier with operation 1 or 2.
     * However, for multiple modifiers it will multiply the modifiers rather than adding them.
     * For example, modifying an attribute with {Amount:2,Operation:2} and {Amount:4,Operation:2} with a Base of 3 results in 45 (3 * (1 + 2) * (1 + 4) = 45).
     */
    public static final int MULTIPLICATIVE_RECURSIVE = 2;

}
