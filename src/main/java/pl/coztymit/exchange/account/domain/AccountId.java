package pl.coztymit.exchange.account.domain;


import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AccountId {

    private UUID uuid;
    private AccountId() {
    }

    public AccountId(UUID uuid) {
        this.uuid = uuid;
    }

    public static AccountId generateNewId() {
        return new AccountId(UUID.randomUUID());
    }
    public boolean equals(AccountId accountId) {
        if (this == accountId) return true;
        return uuid.equals(accountId.uuid);
    }

    public String toString() {
        return uuid.toString();
    }
}
