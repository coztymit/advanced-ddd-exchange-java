package pl.coztymit.exchange.negotiation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessment;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessmentRepository;
import pl.coztymit.exchange.negotiation.domain.risk.RiskNegotiationValue;

import java.util.Optional;

@Service
public class NegotiationAcceptanceService {

    @Autowired
    private RiskAssessmentRepository riskAssessmentRepository;

    public void negotiationAccepted(Negotiation negotiation) {
        Optional<RiskAssessment> optionalRiskAssessment = riskAssessmentRepository.findByNegotiatorId(negotiation.negotiator());
        RiskNegotiationValue riskNegotiationValue = new RiskNegotiationValue(negotiation.proposedExchangeAmount().asMoney());
        RiskAssessment riskAssessment;
        if (optionalRiskAssessment.isPresent()) {
            riskAssessment = optionalRiskAssessment.get();
            riskAssessment.addNegotiation(negotiation.negotiationId(),riskNegotiationValue);
        } else {
            riskAssessment = new RiskAssessment(negotiation.negotiationId(), riskNegotiationValue, negotiation.negotiator());
        }
        riskAssessmentRepository.save(riskAssessment);
    }
}
