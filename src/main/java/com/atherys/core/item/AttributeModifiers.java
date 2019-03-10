package com.atherys.core.item;

public final class AttributeModifiers {

    /**
     * Create a new AttributeModifier with the provided id, slot, amount and operation type
     *
     * @param attributeId The attribute id
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    private static final AttributeModifier modifier(String attributeId, String slot, float amount, int operation) {
        return new AttributeModifier(attributeId, slot, amount, operation).copy();
    }

    /**
     * The maximum health of this mob (in half-hearts); determines the highest health they may be healed to.
     * If you are using this to summon a mob with high health, use this AND the Health tag {Health:200.0f} for example.
     *
     * Applicable to all living entities
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier maxHealth(String slot, float amount, int operation) {
        return modifier("generic.maxHealth", slot, amount, operation);
    }

    /**
     * The range in blocks within which a mob with this attribute will target players or other mobs to track.
     * Exiting this range will cause the mob to cease following the player/mob.
     * Actual value used by most mobs is 16; for zombies it is 40.
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier followRange(String slot, float amount, int operation) {
        return modifier("generic.followRange", slot, amount, operation);
    }

    /**
     * The chance to resist knockback from attacks, explosions, and projectiles. 1.0 is 100% chance for resistance.
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier knockbackResistance(String slot, float amount, int operation) {
        return modifier("generic.knockbackResistance", slot, amount, operation);
    }

    /**
     * Speed of movement in some unknown metric.
     * The mob's maximum speed in blocks/second is a bit over 43 times this value,
     * but can be affected by various conditions, such as:
     * being ridden (if a horse), sprinting, fleeing (if a passive mob), attacking (if an enderman or zombie pigman),
     * being led by a leash, being under the effect of a Speed or Slowness potion, being a baby zombie, or
     * being a witch and drinking a potion.
     * Speed in blocks per second can be calculated with the following equation,
     * where x is the movementSpeed attribute y = 43.178x-0.02141
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier movementSpeed(String slot, float amount, int operation) {
        return modifier("generic.movementSpeed", slot, amount, operation);
    }

    /**
     * Damage dealt by attacks, in half-hearts. This attribute is not found on passive mobs and golems.
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier attackDamage(String slot, float amount, int operation) {
        return modifier("generic.attackDamage", slot, amount, operation);
    }

    /**
     * Armor defense points.
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier armor(String slot, float amount, int operation) {
        return modifier("generic.armor", slot, amount, operation);
    }

    /**
     * Armor Toughness.
     *
     * Applicable to all living entities.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier armorToughness(String slot, float amount, int operation) {
        return modifier("generic.armorToughness", slot, amount, operation);
    }

    /**
     * Determines speed at which attack strength recharges. Value is the number of full-strength attacks per second.
     *
     * Applicable only to players.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier attackSpeed(String slot, float amount, int operation) {
        return modifier("generic.attackSpeed", slot, amount, operation);
    }

    /**
     * Affects the results of loot tables using the quality or bonus_rolls tag
     * (e.g. when opening chests or chest minecarts, fishing, and killing mobs).
     *
     * Applicable only to players.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier luck(String slot, float amount, int operation) {
        return modifier("generic.luck", slot, amount, operation);
    }

    /**
     * Horse jump strength in some unknown metric.
     *
     * Applicable only to horses.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier jumpStrength(String slot, float amount, int operation) {
        return modifier("horse.jumpStrength", slot, amount, operation);
    }

    /**
     * Knockback applied to attacks.
     *
     * Applicable only to illager beasts.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier attackKnockback(String slot, float amount, int operation) {
        return modifier("generic.attackKnockback", slot, amount, operation);
    }

    /**
     * Flight speed modifier in some unknown metric.
     *
     * Applicable only to parrots.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier flyingSpeed(String slot, float amount, int operation) {
        return modifier("generic.flyingSpeed", slot, amount, operation);
    }

    /**
     * Chance that a zombie will spawn another zombie when attacked.
     *
     * Applicable only to zombies.
     *
     * @param slot        the slot ( See: {@link AttributeSlots} )
     * @param amount      the amount
     * @param operation   the operation type ( See: {@link AttributeOperations} )
     * @return The {@link AttributeModifier}
     */
    public static final AttributeModifier spawnReinforcements(String slot, float amount, int operation) {
        return modifier("zombie.spawnReinforcements", slot, amount, operation);
    }
}
