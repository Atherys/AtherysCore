package com.atherys.core.data.player;

import com.atherys.core.data.PlayerDataManager;
import com.atherys.core.database.api.DBObject;
import org.spongepowered.api.entity.living.player.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class PlayerData implements DBObject {

    private UUID uuid;
    private Map<String,Object> data = new HashMap<>();

    private PlayerData ( UUID uuid ) {
        this.uuid = uuid;
    }

    public static <P extends User> PlayerData of ( P user ) {
        return PlayerDataManager.getInstance().get( user.getUniqueId() ).orElse( new PlayerData( user.getUniqueId() ) );
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public Map<String,Object> getData() {
        return data;
    }

    public boolean has ( String id ) {
        return this.data.containsKey( id );
    }

    public boolean set ( String id, Object data ) {
        this.data.put( id, data );
        return true;
    }

    public Optional<Object> get (String id ) {
        return Optional.ofNullable( Optional.of( data.get(id) ) );
    }
}
