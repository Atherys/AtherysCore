package com.atherys.core.party;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.utils.UserUtils;
import org.apache.commons.lang3.RandomUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.spongepowered.api.entity.living.player.User;

import java.util.*;

@Entity
public class Party implements DBObject {

    @Id
    private UUID uuid;

    private UUID leader;
    private boolean pvp;

    private List<UUID> members = new ArrayList<>();

    private <T extends User, C extends Collection<T>> Party(T leader, C members) {
        this.uuid = UUID.randomUUID();
        this.leader = leader.getUniqueId();
        members.forEach(this::addMember);
    }

    public static <T extends User, C extends Collection<T>> Party of(T leader, C members) {
        Party party = new Party(leader, members);
        PartyManager.getInstance().register(party);
        return party;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public UUID getLeaderUUID() {
        return leader;
    }

    public List<UUID> getMemberUUIDs() {
        return members;
    }

    public Optional<? extends User> getLeader() {
        return UserUtils.getUser(leader);
    }

    public List<User> getMembers() {
        List<User> users = new ArrayList<>(members.size());
        members.forEach(member -> UserUtils.getUser(member).map(users::add));
        return users;
    }

    public <T extends User> void addMember(T user) {
        PartyManager.getInstance().setUserParty(user, this);
        this.members.add(user.getUniqueId());
    }

    /**
     * Removes a member from this party. If the number of remaining members are <= 1, then this returns true.
     * @param user The user to be removed from the party
     */
    public <T extends User> boolean removeMember(T user) {
        if ( this.members.contains(user.getUniqueId()) ) {
            PartyManager.getInstance().removeUserParty(user);
            this.members.remove(user.getUniqueId());

            // If only 1 member is left in the party, remove it
            if ( members.size() <= 1 ) {
                PartyManager.getInstance().removeParty(this);
                return true;
            }

            // If the user that was removed from the party was the party leader, find a random member and set them as leader
            if ( user.getUniqueId().equals(leader) ) setRandomLeader();
        }
        return false;
    }

    public <T extends User> boolean isMember(T user) {
        Optional<Boolean> result = PartyManager.getInstance().getUserParty(user).map(party -> party.equals(this));
        return result.orElse(false);
    }

    public <T extends User> void setLeader(T user) {
        if ( members.contains(user.getUniqueId()) ) leader = user.getUniqueId();
    }

    public <T extends User> boolean isLeader(T user) {
        return isMember(user) && user.getUniqueId().equals(this.getLeaderUUID());
    }

    public void setPvp(boolean state) {
        this.pvp = state;
    }

    public boolean hasPvp() {
        return pvp;
    }

    private void setRandomLeader() {
        this.leader = members.get(RandomUtils.nextInt(0, members.size() - 1));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Party && ((Party) other).getUUID().equals(this.getUUID());
    }
}
