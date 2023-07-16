package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.*;
import pl.coztymit.exchange.negotiation.domain.NegotiationId;

@Entity
@Table(name = "risk_lines")
public class RiskLine {

    @EmbeddedId
    @AttributeOverride(name = "uuid", column = @Column(name = "risk_line_id"))
    private RiskLineId riskLineId;

    @AttributeOverride(name = "uuid", column = @Column(name = "negotiation_id"))
    private NegotiationId negotiationId;

    @AttributeOverrides({
            @AttributeOverride(name = "value.value", column = @Column(name = "risk_negotiation_value_amount")),
            @AttributeOverride(name = "value.currency.value", column = @Column(name = "risk_negotiation_value_currency"))
    })
    private RiskNegotiationValue riskNegotiationValue;

    private RiskLine() {
    }

    public RiskLine(NegotiationId negotiationId, RiskNegotiationValue riskNegotiationValue) {
        this.riskLineId = RiskLineId.generate();
        this.negotiationId = negotiationId;
        this.riskNegotiationValue = riskNegotiationValue;
    }

}
