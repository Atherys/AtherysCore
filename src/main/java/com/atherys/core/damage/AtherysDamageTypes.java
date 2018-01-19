package com.atherys.core.damage;

import com.atherys.core.damage.sources.AtherysDamageSources;

public final class AtherysDamageTypes {

    private AtherysDamageTypes() {}

    // Melee
    public static final AtherysDamageType BLUNT = new AtherysDamageType( "atherys:blunt", "Blunt", AtherysDamageSources::bluntDamage );

    public static final AtherysDamageType STAB = new AtherysDamageType( "atherys:stab", "Stabbing", AtherysDamageSources::stabDamage );

    public static final AtherysDamageType SLASH = new AtherysDamageType( "atherys:slash", "Slashing", AtherysDamageSources::slashDamage );

    public static final AtherysDamageType UNARMED = new AtherysDamageType( "atherys:unarmed", "Unarmed", AtherysDamageSources::unarmedDamage );

    // Ranged
    public static final AtherysDamageType PIERCE = new AtherysDamageType( "atherys:pierce", "Piercing", AtherysDamageSources::piercingDamage );

    public static final AtherysDamageType BALLISTIC = new AtherysDamageType( "atherys:ballistic", "Ballistic", AtherysDamageSources::ballisticDamage );

    // Magic
    public static final AtherysDamageType FIRE = new AtherysDamageType( "atherys:fire", "Fire", AtherysDamageSources::fireDamage );

    public static final AtherysDamageType ICE = new AtherysDamageType( "atherys:ice", "Ice", AtherysDamageSources::iceDamage );

    public static final AtherysDamageType ARCANE = new AtherysDamageType( "atherys:arcane", "Arcane", AtherysDamageSources::arcaneDamage );

    public static final AtherysDamageType SHOCK = new AtherysDamageType ( "atherys:shock", "Shock", AtherysDamageSources::shockDamage );

    public static final AtherysDamageType NATURE = new AtherysDamageType( "atherys:nature", "Nature", AtherysDamageSources::natureDamage );

    public static final AtherysDamageType MENTAL = new AtherysDamageType( "atherys:mental", "Mental", AtherysDamageSources::mentalDamage );

    public static final AtherysDamageType RADIANT = new AtherysDamageType( "atherys:radiant", "Radiant", AtherysDamageSources::radiantDamage );

    public static final AtherysDamageType NECROTIC = new AtherysDamageType( "atherys:necrotic", "Necrotic", AtherysDamageSources::necroticDamage );

    public static final AtherysDamageType BLOOD = new AtherysDamageType( "atherys:blood", "Blood", AtherysDamageSources::bloodDamage );

}
