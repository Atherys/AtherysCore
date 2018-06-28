package com.atherys.core;

import com.atherys.core.party.data.PartyData;
import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.UUID;

public final class CoreKeys {

    CoreKeys() {
    }

    public static Key<Value<UUID>> PARTY;

    static DataRegistration<PartyData, PartyData.Immutable> PARTY_DATA_REGISTRATION;

    static {
        PARTY = Key.builder()
                .type(new TypeToken<Value<UUID>>() {})
                .id("party")
                .name("Party")
                .query(DataQuery.of("atheryscore", "Party"))
                .build();
    }

}
