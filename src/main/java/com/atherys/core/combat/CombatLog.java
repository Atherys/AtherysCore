package com.atherys.core.combat;

import com.atherys.core.AtherysCore;
import com.google.inject.internal.cglib.proxy.$Enhancer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.util.Tuple;

import java.util.*;

public class CombatLog {

    private static class Entry {

        private UUID attacker;

        private UUID victim;

        private Long initiationTime;

        public Entry(UUID attacker, UUID victim, Long initiationTime) {
            this.attacker = attacker;
            this.victim = victim;
            this.initiationTime = initiationTime;
        }

        public UUID getAttacker() {
            return attacker;
        }

        public void setAttacker(UUID attacker) {
            this.attacker = attacker;
        }

        public UUID getVictim() {
            return victim;
        }

        public void setVictim(UUID victim) {
            this.victim = victim;
        }

        public Long getInitiationTime() {
            return initiationTime;
        }

        public void setInitiationTime(Long initiationTime) {
            this.initiationTime = initiationTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(getAttacker(), entry.getAttacker()) &&
                    Objects.equals(getVictim(), entry.getVictim()) &&
                    Objects.equals(getInitiationTime(), entry.getInitiationTime());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAttacker(), getVictim(), getInitiationTime());
        }
    }

    private Set<Entry> log = new HashSet<>();

    private Task cleanup;

    public void init() {
        cleanup = Task.builder()
                .intervalTicks(20)
                .execute(() -> {
                    long currentMillis = System.currentTimeMillis();
                    // remove all combat mappings whose duration has expired
                    log.removeIf(entry -> (currentMillis - entry.getInitiationTime()) >= AtherysCore.getConfig().COMBAT_LIMIT.toMillis());
                })
                .submit(AtherysCore.getInstance());
    }

    /**
     * If these 2 players are currently engaged in combat, this method
     * will provide the player who initiated the combat by first damaging
     * the other.
     *
     * @param playerA Player A
     * @param playerB Player B
     * @return The attacking player, if any
     */
    public Optional<Player> fetchAttacker(Player playerA, Player playerB) {
        return log.stream()
                .filter(entry ->
                        (entry.getAttacker().equals(playerA.getUniqueId()) && entry.getVictim().equals(playerB.getUniqueId())) ||
                        (entry.getAttacker().equals(playerB.getUniqueId()) && entry.getVictim().equals(playerA.getUniqueId()))
                )
                .map(entry -> {
                    if (entry.getAttacker().equals(playerA.getUniqueId())) {
                        return playerA;
                    } else {
                        return playerB;
                    }
                })
                .findAny();
    }

    /**
     * Inverse method of {@link #fetchAttacker(Player, Player)}
     *
     * @param playerA Player A
     * @param playerB Player B
     * @return The attacking player, if any
     */
    public Optional<Player> fetchVictim(Player playerA, Player playerB) {
        return fetchAttacker(playerA, playerB).map(attacker -> {
            if (attacker.getUniqueId().equals(playerA.getUniqueId())) {
                return playerB;
            } else {
                return playerA;
            }
        });
    }

    public Optional<Long> fetchLatestCombatLogTimestamp(Player player) {
        return log.stream()
                .filter(entry -> entry.getAttacker().equals(player.getUniqueId()) || entry.getVictim().equals(player.getUniqueId()))
                .map(Entry::getInitiationTime)
                .sorted()
                .findFirst();
    }

    public void initiateCombat(Player attacker, Player victim) {
        log.add(new Entry(attacker.getUniqueId(), victim.getUniqueId(), System.currentTimeMillis()));
    }

    public void endCombat(Player attacker, Player victim) {
        log.removeIf(entry -> entry.getAttacker().equals(attacker.getUniqueId()) && entry.getVictim().equals(victim.getUniqueId()));
    }

    public boolean isInCombat(Player player) {
        return log.stream().anyMatch(entry -> entry.getAttacker().equals(player.getUniqueId()) || entry.getVictim().equals(player.getUniqueId()));
    }
}
