package com.atherys.core.party;

import com.atherys.core.database.impl.MongoCoreDatabase;
import com.atherys.core.database.impl.MongoDatabaseManager;
import com.atherys.core.utils.UserUtils;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.spongepowered.api.entity.living.player.User;

import java.util.*;

/**
 * The primary class responsible for tracking all parties and their members.
 * Is also responsible for saving/loading parties and their members to/from the database.
 */
public final class PartyManager extends MongoDatabaseManager<Party> {

    private static final PartyManager instance = new PartyManager();

    private Map<UUID,Party> playerPartyMap = new HashMap<>();

    private PartyManager() {}

    @Override
    protected MongoCollection<Document> collection() {
        return MongoCoreDatabase.getInstance().getDatabase().getCollection("parties");
    }

    @Override
    protected Document toDocument ( Party object ) {
        Document doc = new Document( "uuid", object.getUUID() );

        doc.append( "leader", object.getLeader().getUniqueId() );

        List<UUID> members = getPartyMemberUUIDs( object );
        doc.append( "members", members );

        return doc;
    }

    @Override
    protected boolean fromDocument ( Document doc ) {
        // get uuid of party
        UUID partyUUID = doc.get( "uuid", UUID.class );
        // create new party from uuid, is not added to playerPartyMap
        Party party = new Party ( partyUUID );

        // get leader UUID
        UUID leaderUUID = doc.get( "leader", UUID.class );
        // get leader User from UUID
        Optional<? extends User> leader = UserUtils.getUser( leaderUUID );
        // if leader User is not present, there is no point in proceeding, party will not be loaded and will effectively be removed.
        if ( !leader.isPresent() ) return false;

        // otherwise, set the leader of the party
        party.setLeader( leader.get() );

        // load the rest of the members
        List members = doc.get( "members", List.class );

        // Set leader's party along with all members' parties.
        playerPartyMap.put( leaderUUID, party );
        for ( Object uuid : members ) {
            playerPartyMap.put( (UUID) uuid, party );
        }

        // if the members of the party are 1 or less ( 0 ), remove the party as there is no point in a 1-player party. Otherwise, count the party as properly loaded.
        if ( party.getMembers().size() <= 1 ) {
            party.remove();
            return false;
        }
        else return true;
    }

    /**
     * Save all parties and their members to the database.
     */
    @Override
    public void saveAll() {
        saveAll( getParties() );
    }

    /**
     * Used to get the UUIDs of the members in the party. Is less intensive than {@link PartyManager#getPartyMembers(Party)} since it does not look up the User object of each UUID.
     * @param party The party object whose members are going to be looked up.
     * @return A list of UUIDs representing the players who are in this party.
     */
    public List<UUID> getPartyMemberUUIDs ( Party party ) {
        List<UUID> partyMembers = new ArrayList<>();

        playerPartyMap.forEach( (k,v) -> {
            if ( v.getUUID().equals(party.getUUID()) ) {
                partyMembers.add( k );
            }
        });

        return partyMembers;
    }

    /**
     * Used to get the members in the party. Is more intensive than {@link PartyManager#getPartyMemberUUIDs(Party)} since it uses {@link UserUtils} to look up all UUIDs and get their respective users. Is a user could not be found based on their UUID, they are not included in the final list of party members.
     * @param party The party whose members are going to be looked up
     * @return A list of {@link User}s who are part of this party.
     */
    public List<User> getPartyMembers ( Party party ) {
        List<User> partyMembers = new ArrayList<>();

        playerPartyMap.forEach( (k,v) -> {
            if ( v.getUUID().equals( party.getUUID() ) ) {
                Optional<? extends User> user = UserUtils.getUser(k);
                if ( user.isPresent() ) partyMembers.add( user.get() );
                else playerPartyMap.remove( k );
            }
        });

        return partyMembers;
    }

    /**
     * Used to get all parties currently existing in the game.
     * @return A list of all relevant {@link Party} objects.
     */
    public List<Party> getParties() {
        List<Party> uniqueParties = new ArrayList<>();

        playerPartyMap.values().forEach( party -> {
            if ( !uniqueParties.contains( party ) ) uniqueParties.add( party );
        });

        return uniqueParties;
    }

    /**
     * Used to look up if a player is in a {@link Party}.
     * @param player The player being looked up
     * @return Whether or not this player is in a party.
     */
    public boolean hasPlayerParty ( User player ) {
        return playerPartyMap.containsKey( player.getUniqueId() );
    }

    /**
     * Used to remove a player from their current {@link Party}.
     * @param player The player to be removed.
     */
    public void resetPlayerParty ( User player ) {
        playerPartyMap.remove( player.getUniqueId() );
    }

    /**
     * Used to set the {@link Party} of a player. WARNING: This does not check if a player is in another party beforehand. This will effectively change the player's party if it is used on a player who is already in another party.
     * @param player The player whose party is to be set.
     * @param party The party the player will be part of.
     */
    public void setPlayerParty ( User player, Party party ) {
        playerPartyMap.put( player.getUniqueId(), party );
    }

    /**
     * Used to get the {@link Party} of a player
     * @param player The player whose party is to be looked up
     * @return An {@link Optional} of the player's party. If the party was not found, this is empty.
     */
    public Optional<Party> getPlayerParty ( User player ) {
        return Optional.ofNullable( playerPartyMap.get( player.getUniqueId() ) );
    }

    /**
     * Adds a new party to the {@link PartyManager}. This will use {@link PartyManager#setPlayerParty(User, Party)} to change every provided player's party.
     * @param party The {@link Party} to be added.
     * @param members The member {@link User}s
     */
    public void addParty ( Party party, User... members ) {
        for ( User user : members ) {
            setPlayerParty ( user, party );
        }
    }

    /**
     * Removes a party from the {@link PartyManager}. This will remove every player's UUID whose party is the same as the one provided, therefore making the corresponding players party-less.
     * @param party The party to be removed.
     */
    public void removeParty ( Party party ) {
        playerPartyMap.forEach( (k,v) -> {
            if ( v.getUUID().equals( party.getUUID() ) ) playerPartyMap.remove( k );
        });
    }

    public static PartyManager getInstance() {
        return instance;
    }
}
