package com.atherys.core.economy;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public final class Economy {

    public static boolean isPresent() {
        return AtherysCore.getEconomyService().isPresent();
    }

    public static void transferCurrency(UUID source, UUID destination, Currency currency, BigDecimal amount, Cause cause) {
        getAccount(source).ifPresent(sourceAccount -> getAccount(destination).ifPresent(destinationAccount -> {
            transfer(sourceAccount, destinationAccount, currency, amount, cause);
        }));
    }

    public static void transferCurrency(UUID source, String destination, Currency currency, BigDecimal amount, Cause cause) {
        getAccount(source).ifPresent(sourceAccount -> getAccount(destination).ifPresent(destinationAccount -> {
            transfer(sourceAccount, destinationAccount, currency, amount, cause);
        }));
    }

    public static void transferCurrency(String source, UUID destination, Currency currency, BigDecimal amount, Cause cause) {
        getAccount(source).ifPresent(sourceAccount -> getAccount(destination).ifPresent(destinationAccount -> {
            transfer(sourceAccount, destinationAccount, currency, amount, cause);
        }));
    }

    public static void transferCurrency(String source, String destination, Currency currency, BigDecimal amount, Cause cause) {
        getAccount(source).ifPresent(sourceAccount -> getAccount(destination).ifPresent(destinationAccount -> {
            transfer(sourceAccount, destinationAccount, currency, amount, cause);
        }));
    }

    private static void transfer(Account source, Account destination, Currency currency, BigDecimal amount, Cause cause) {
        source.transfer(
                destination,
                currency,
                amount,
                cause
        );
    }

    private static Optional<Account> getAccount(String account) {
        return AtherysCore.getEconomyService().flatMap(econ -> econ.getOrCreateAccount(account));
    }

    private static Optional<UniqueAccount> getAccount(UUID account) {
        return AtherysCore.getEconomyService().flatMap(econ -> econ.getOrCreateAccount(account));
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
