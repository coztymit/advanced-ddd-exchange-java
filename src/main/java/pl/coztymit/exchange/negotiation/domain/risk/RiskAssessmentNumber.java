package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class RiskAssessmentNumber {
    private UUID uuid;

    private RiskAssessmentNumber() {
    }

    public RiskAssessmentNumber(UUID uuid) {
        this.uuid = uuid;
    }

    public static RiskAssessmentNumber generate() {
        return new RiskAssessmentNumber(UUID.randomUUID());
    }
}
