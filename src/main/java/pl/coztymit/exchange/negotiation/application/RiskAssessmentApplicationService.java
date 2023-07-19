package pl.coztymit.exchange.negotiation.application;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.NegotiationId;
import pl.coztymit.exchange.negotiation.domain.Negotiator;
import pl.coztymit.exchange.negotiation.domain.risk.*;

import java.util.Optional;
import java.util.UUID;

@Service
public class RiskAssessmentApplicationService {

    @Autowired
    private RiskAssessmentRepository riskAssessmentRepository;

    @Transactional
    public ChangeRiskAssessmentRiskLevelStatus changeRiskAssessmentRiskLevel(UUID riskAssessmentNumber, String riskLevel) {
        Optional<RiskAssessment> byRiskAssessmentNumber = riskAssessmentRepository.findByRiskAssessmentNumber(new RiskAssessmentNumber(riskAssessmentNumber));
        byRiskAssessmentNumber
                .ifPresent(riskAssessment -> {
                    riskAssessment.changeRiskLevel(new RiskLevel(riskLevel));
                    riskAssessmentRepository.save(riskAssessment);
                });

        if (byRiskAssessmentNumber.isEmpty()) {
            return new ChangeRiskAssessmentRiskLevelStatus("Risk assessment not found");
        }
        return new ChangeRiskAssessmentRiskLevelStatus("OK");
    }

    @Transactional
    public CreateRiskAssessmentStatus negotiationApproved(NegotiationId negotiationId, Negotiator negotiator, Money proposedExchangeAmount) {
        Optional<RiskAssessment> optionalRiskAssessment = riskAssessmentRepository.findByNegotiator(negotiator);
        RiskNegotiationValue riskNegotiationValue = new RiskNegotiationValue(proposedExchangeAmount);
        RiskAssessment riskAssessment;
        if (optionalRiskAssessment.isPresent()) {
            riskAssessment = optionalRiskAssessment.get();
            riskAssessment.addNegotiation(negotiationId, riskNegotiationValue);

        } else {
            riskAssessment = new RiskAssessment(negotiationId, riskNegotiationValue, negotiator);
        }
        riskAssessmentRepository.save(riskAssessment);
        return new CreateRiskAssessmentStatus("OK");
    }
}
