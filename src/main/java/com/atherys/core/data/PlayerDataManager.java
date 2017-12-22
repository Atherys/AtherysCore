package com.atherys.core.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO: Create a manager class which will persist arbitrary player-bound data in a database.
public final class PlayerDataManager {

    private Map<UUID, Object> data = new HashMap<>();

}
