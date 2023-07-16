package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Money;

@Embeddable
public class RiskNegotiationValue {

    private Money value;

    private RiskNegotiationValue() {
    }

    public RiskNegotiationValue(Money value) {
        if(value == null || value.isNegative()) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        this.value = value;
    }

    public RiskNegotiationValue add(RiskNegotiationValue riskNegotiationValue) {
        return new RiskNegotiationValue(this.value.add(riskNegotiationValue.value));
    }
}
