package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class RiskLineId {
    private UUID uuid;

    private RiskLineId() {
    }

    public RiskLineId(UUID uuid) {
        this.uuid = uuid;
    }

    static RiskLineId generate() {
        return new RiskLineId(UUID.randomUUID());
    }
}
