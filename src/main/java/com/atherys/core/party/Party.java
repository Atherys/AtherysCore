package com.atherys.core.party;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.utils.UserUtils;
import org.apache.commons.lang3.RandomUtils;
import org.spongepowered.api.entity.living.player.User;

import java.util.List;
import java.util.UUID;

public class Party implements DBObject {

    private UUID partyUUID;

    private UUID leader;

    Party ( UUID uuid ) {
        this.partyUUID = uuid;
    }

    private Party ( User... members ) {
        if ( members.length == 0 ) return;

        partyUUID = UUID.randomUUID();

        this.leader = members[0].getUniqueId();
        for (User player : members) {
            addPlayer(player);
        }

        PartyManager.getInstance().addParty ( this, members );
    }

    public static Party fromLeader ( User player ) {
        return new Party ( player );
    }

    public static Party of ( User leader, User... members ) {
        Party party = Party.fromLeader( leader );

        for (User player : members) {
            party.addPlayer(player);
        }

        return party;
    }

    @Override
    public UUID getUUID() {
        return partyUUID;
    }

    public boolean hasPlayer ( User player ) {
        return PartyManager.getInstance().hasPlayerParty( player );
    }

    public void addPlayer ( User player ) {
        if ( !hasPlayer( player ) ) {
            PartyManager.getInstance().setPlayerParty( player, this );
        }
    }

    public void removePlayer ( User player ) {
        PartyManager.getInstance().resetPlayerParty( player );

        List<User> members = getMembers();

        if ( members.size() <= 1 ) {
            this.remove();
            return;
        }

        if ( player.getUniqueId().equals( leader ) ) {
            setLeader( members.get( RandomUtils.nextInt(0, members.size() - 1) ) );
        }
    }

    public boolean isLeader ( UUID uuid ) {
        return leader.equals( uuid );
    }

    public boolean isLeader ( User player ) {
        return leader.equals( player.getUniqueId() );
    }

    public User getLeader() {
        return UserUtils.getUser( leader ).orElse(null);
    }

    public void setLeader ( User newLeader ) {
        leader = newLeader.getUniqueId();
    }

    private void setLeader ( UUID uuid ) { leader = uuid; }

    public List<User> getMembers() {
        return PartyManager.getInstance().getPartyMembers ( this );
    }

    public void remove() {
        PartyManager.getInstance().removeParty ( this );
    }

}
