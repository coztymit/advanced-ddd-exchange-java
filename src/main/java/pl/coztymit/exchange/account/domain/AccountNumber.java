package pl.coztymit.exchange.account.domain;


import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AccountNumber {

    private UUID uuid;

    private AccountNumber() {
    }

    public AccountNumber(UUID uuid) {
        this.uuid = uuid;
    }

    public AccountNumber(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public static AccountNumber generateNewId() {
        return new AccountNumber(UUID.randomUUID());
    }

    public boolean equals(AccountNumber accountNumber) {
        if (this == accountNumber) return true;
        return uuid.equals(accountNumber.uuid);
    }

    public String toString() {
        return uuid.toString();
    }
}
