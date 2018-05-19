package com.atherys.core.party;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.utils.UserUtils;
import org.apache.commons.lang3.RandomUtils;
import org.spongepowered.api.entity.living.player.User;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Represents a party of players. This class only stores the party {@link UUID} and the leader of
 * the party's UUID. The party does not store information on which members are part of it. That job
 * falls to the {@link PartyManager}. Any methods related to party members in this class will have
 * to utilize the PartyManager in some way or another.
 */
public class Party implements DBObject {

    private UUID partyUUID;

    private UUID leader;

    Party(UUID uuid) {
        this.partyUUID = uuid;
    }

    private Party(User... members) {
        if (members.length == 0) {
            return;
        }

        partyUUID = UUID.randomUUID();

        this.leader = members[0].getUniqueId();
        for (User player : members) {
            addPlayer(player);
        }

        PartyManager.getInstance().addParty(this, members);
    }

    /**
     * Creates a new single-player party. If this constructor is used, a second player must be added
     * immediately afterwards.
     *
     * @param player The party leader
     * @return The party instance
     */
    public static Party fromLeader(User player) {
        return new Party(player);
    }

    /**
     * Creates a party of players.
     *
     * @param leader  The leader of the party
     * @param members The remaining member of the party, EXCLUDING the leader.
     * @return The party instance
     */
    public static Party of(User leader, User... members) {
        Party party = Party.fromLeader(leader);

        for (User player : members) {
            party.addPlayer(player);
        }

        return party;
    }

    /**
     * A party is tracked using it's {@link UUID}. This gets it.
     *
     * @return The UUID of the party.
     */
    @Override
    public UUID getUUID() {
        return partyUUID;
    }

    /**
     * Uses the {@link PartyManager#hasPlayerParty(User)} to figure out if the given player is part of
     * this party.
     *
     * @param player The player to be looked up.
     * @return Whether or not this player is in the party.
     */
    public boolean hasPlayer(User player) {
        return PartyManager.getInstance().hasPlayerParty(player);
    }

    /**
     * Uses the {@link PartyManager#setPlayerParty(User, Party)} to add a player to the party. Checks
     * beforehand using {@link PartyManager#hasPlayerParty(User)} to check if the given player already
     * has a party. If so, the player's party will not be changed.
     *
     * @param player The player to be added.
     */
    public void addPlayer(User player) {
        if (!hasPlayer(player)) {
            PartyManager.getInstance().setPlayerParty(player, this);
        }
    }

    /**
     * Uses the {@link PartyManager#resetPlayerParty(User)} to remove a player from this party. If the
     * player is the leader of the party, a new leader will be randomly selected from the remaining
     * members. If the number of remaining members is less than or equal to 1, the party will be
     * removed and all remaining members will become party-less.
     *
     * @param player The player to be removed.
     */
    public void removePlayer(User player) {
        PartyManager.getInstance().resetPlayerParty(player);

        List<User> members = getMembers();

        if (members.size() <= 1) {
            this.remove();
            return;
        }

        if (player.getUniqueId().equals(leader)) {
            setLeader(members.get(RandomUtils.nextInt(0, members.size() - 1)));
        }
    }

    /**
     * Used to check if the given player is the leader of this party.
     *
     * @param player The player to be checked
     * @return Whether or not the player is the leader of this party.
     */
    public boolean isLeader(User player) {
        return leader.equals(player.getUniqueId());
    }

    /**
     * Uses {@link UserUtils#getUser(UUID)} to get the leader of this party. If the leader could not
     * be found, returns null.
     *
     * @return The leader {@link User} of this party.
     */
    @Nullable
    public User getLeader() {
        return UserUtils.getUser(leader).orElse(null);
    }

    /**
     * Used to change the leader of this party.
     *
     * @param newLeader The new leader of the party.
     */
    public void setLeader(User newLeader) {
        leader = newLeader.getUniqueId();
    }

    /**
     * Uses {@link PartyManager#getPartyMembers(Party)} to retrieve all members of this party.
     *
     * @return A list of {@link User}s who are associated with this party.
     */
    public List<User> getMembers() {
        return PartyManager.getInstance().getPartyMembers(this);
    }

    /**
     * Removes this party using {@link PartyManager#removeParty(Party)}.
     */
    public void remove() {
        PartyManager.getInstance().removeParty(this);
    }

}
