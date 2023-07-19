package pl.coztymit.exchange.promotion.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class PromotionNumber {
    private UUID uuid;

    private PromotionNumber() {
    }

    public PromotionNumber(UUID uuid) {
        this.uuid = uuid;
    }

    public PromotionNumber(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public static PromotionNumber generateNewId() {
        return new PromotionNumber(UUID.randomUUID());
    }
}
