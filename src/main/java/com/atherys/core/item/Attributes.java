package com.atherys.core.item;

public final class Attributes {

    private static final AttributeModification modification(String attributeId, String slot, float amount, int operation) {
        return new AttributeModification(attributeId, slot, amount, operation).copy();
    }

    public static final AttributeModification maxHealth(String slot, float amount, int operation) {
        return modification("generic.maxHealth", slot, amount, operation);
    }

    public static final AttributeModification followRange(String slot, float amount, int operation) {
        return modification("generic.followRange", slot, amount, operation);
    }

    public static final AttributeModification knockbackResistance(String slot, float amount, int operation) {
        return modification("generic.knockbackResistance", slot, amount, operation);
    }

    public static final AttributeModification movementSpeed(String slot, float amount, int operation) {
        return modification("generic.movementSpeed", slot, amount, operation);
    }

}
