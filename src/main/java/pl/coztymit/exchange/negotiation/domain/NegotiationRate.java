package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public class NegotiationRate {
    private BigDecimal proposedRate;
    private BigDecimal baseExchangeRate;
    private BigDecimal differenceInPercentage;

    private NegotiationRate() {
    }

    public NegotiationRate(BigDecimal proposedRate, BigDecimal baseExchangeRate) {
        this.proposedRate = proposedRate;
        this.baseExchangeRate = baseExchangeRate;
        this.differenceInPercentage = calculateDifferenceInPercentage();
    }

    private BigDecimal calculateDifferenceInPercentage() {
        BigDecimal subtract = baseExchangeRate.subtract(proposedRate);
        return subtract.divide(baseExchangeRate, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));

    }
    public BigDecimal differenceInPercentage() {
        return differenceInPercentage;
    }
}
