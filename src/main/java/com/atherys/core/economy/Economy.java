package com.atherys.core.economy;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public final class Economy {

    public static boolean isPresent() {
        return AtherysCore.getEconomyService().isPresent();
    }

    public static void transferCurrency(UUID source, UUID destination, Currency currency, BigDecimal amount, Cause cause) {
        Optional<EconomyService> economyService = AtherysCore.getEconomyService();

        economyService.ifPresent(econ -> {
            econ.getOrCreateAccount(source).ifPresent(sourceAccount -> {
                econ.getOrCreateAccount(destination).ifPresent(destinationAccount -> {
                    sourceAccount.transfer(
                            destinationAccount,
                            currency,
                            amount,
                            cause
                    );
                });
            });
        });
    }

    public static void addCurrency(UUID account, Currency currency, BigDecimal amount, Cause cause) {
        AtherysCore.getEconomyService().ifPresent(econ -> {
            econ.getOrCreateAccount(account).ifPresent(uniqueAccount -> {
                uniqueAccount.deposit(currency, amount, cause);
            });
        });
    }

    public static void removeCurrency(UUID account, Currency currency, BigDecimal amount, Cause cause) {
        AtherysCore.getEconomyService().ifPresent(econ -> {
            econ.getOrCreateAccount(account).ifPresent(uniqueAccount -> {
                uniqueAccount.withdraw(currency, amount, cause);
            });
        });
    }

}
