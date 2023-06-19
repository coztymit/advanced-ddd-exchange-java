package pl.coztymit.exchange.account.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;
@Embeddable
public class WalletId {
    private UUID value;
    private WalletId() {
    }

    public WalletId(UUID value) {
        this.value = value;
    }
}
