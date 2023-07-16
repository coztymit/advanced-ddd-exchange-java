package pl.coztymit.exchange.negotiation.domain.risk;

import jakarta.persistence.*;
import pl.coztymit.exchange.negotiation.domain.NegotiationId;
import pl.coztymit.exchange.negotiation.domain.Negotiator;

import java.util.ArrayList;
import java.util.List;

//Agregat oceny ryzyka
@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "risk_assessment_number"))
    @EmbeddedId
    private RiskAssessmentNumber riskAssessmentNumber;
    @AttributeOverride(name = "identityId.uuid", column = @Column(name = "negotiator_identity_id"))
    private Negotiator negotiator;
    @AttributeOverride(name = "level", column = @Column(name = "risk_level"))
    private RiskLevel riskLevel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "risk_assessment_number", nullable = false)
    private List<RiskLine> riskLines = new ArrayList<>();


    private RiskAssessment() {

    }

    public RiskAssessment(NegotiationId negotiationId, RiskNegotiationValue riskNegotiationValue, Negotiator negotiator) {
        this.riskAssessmentNumber = RiskAssessmentNumber.generate();
        this.negotiator = negotiator;
        this.riskLevel = RiskLevel.LOW;
        this.riskLines.add(new RiskLine(negotiationId, riskNegotiationValue));
    }

    public void addNegotiation(NegotiationId negotiationId, RiskNegotiationValue riskNegotiationValue) {
        this.riskLines.add(new RiskLine(negotiationId, riskNegotiationValue));
        if (this.riskLines.size() > 10 && this.riskLines.size() < 50) {
            this.riskLevel = RiskLevel.MEDIUM;
        } else if (this.riskLines.size() > 50) {
            this.riskLevel = RiskLevel.HIGH;
        }
    }

    public void changeRiskLevel(RiskLevel riskLevel) {
        if(this.riskLevel != riskLevel){
            this.riskLevel = riskLevel;
        }
    }
}
