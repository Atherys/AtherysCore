package com.atherys.core.party;

import com.atherys.core.AtherysCore;
import com.atherys.core.database.MongoCoreDatabase;
import com.atherys.core.database.mongo.MorphiaDatabaseManager;
import com.atherys.core.party.data.PartyData;
import org.spongepowered.api.entity.living.player.User;

import java.util.Optional;
import java.util.UUID;

/**
 * The primary class responsible for tracking all parties and their members. Is also responsible for
 * saving/loading parties and their members to/from the database.
 */
public final class PartyManager extends MorphiaDatabaseManager<Party> {

    private static final PartyManager instance = new PartyManager();

    private PartyManager() {
        super(MongoCoreDatabase.getInstance(), AtherysCore.getInstance().getLogger(), Party.class);
    }

    public static PartyManager getInstance() {
        return instance;
    }

    public void register(Party party) {
        this.getCache().put(party.getUUID(), party);
    }

    public Optional<Party> getParty(UUID partyUUID) {
        return Optional.ofNullable(getCache().get(partyUUID));
    }

    public <T extends User> boolean hasUserParty(T user) {
        return getUserParty(user).isPresent();
    }

    public <T extends User> Optional<Party> getUserParty(T user) {
        Optional<PartyData> partyData = user.get(PartyData.class);
        if (partyData.isPresent()) return partyData.get().getParty();
        else return Optional.empty();
    }

    public <T extends User> void setUserParty(T user, Party party) {
        user.offer(new PartyData(party.getUUID()));
    }

    public <T extends User> void removeUserParty(T user) {
        user.remove(PartyData.class);
    }

    /**
     * Checks if the provided users are in the same party.
     * If both users are in a party, and both parties share the same UUID (i.e. they are the same ), this returns true.
     * Under any other circumstances, including if neither user is in a party, this will return false.
     *
     * @param user1 The first user
     * @param user2 The second user
     */
    public <T extends User> boolean areUsersInSameParty(User user1, User user2) {
        Optional<Party> party1 = getUserParty(user1);
        Optional<Party> party2 = getUserParty(user2);
        return party1.isPresent() && party2.isPresent() && party1.get().equals(party2.get());
    }

    public void removeParty(Party party) {
        party.getMembers().forEach(this::removeUserParty);
        this.getCache().remove(party.getUUID());
        this.remove(party);
    }

    public void saveAll() {
        saveAll(getCache().values());
    }
}
