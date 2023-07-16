package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.Embeddable;

@Embeddable
public class RiskLevel {
    public static final RiskLevel LOW = new RiskLevel("LOW");
    public static final RiskLevel MEDIUM = new RiskLevel("MEDIUM");
    public static final RiskLevel HIGH = new RiskLevel("HIGH");

    private String level;

    private RiskLevel() {
    }

    public RiskLevel(String level) {
        if(level == null || !(level.equals("LOW") || level.equals("MEDIUM") || level.equals("HIGH"))) {
            throw new IllegalArgumentException("Invalid risk level");
        }
        this.level = level;
    }

}
