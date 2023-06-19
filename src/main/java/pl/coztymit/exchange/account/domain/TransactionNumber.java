package pl.coztymit.exchange.account.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.util.UUID;
@Embeddable
public class TransactionNumber {
    private UUID value;
    private TransactionNumber() {
    }

    private TransactionNumber(UUID value) {
        this.value = value;
    }

    public static TransactionNumber generate() {
        return new TransactionNumber(UUID.randomUUID());
    }
}
