package pl.coztymit.exchange.negotiation.domain.risk;

import pl.coztymit.exchange.negotiation.domain.Negotiator;

import java.util.Optional;

public interface RiskAssessmentRepository {
        void save(RiskAssessment riskAssessment);
        Optional<RiskAssessment> findByNegotiatorId(Negotiator negotiator);
        Optional<RiskAssessment> findByRiskAssessmentNumber(RiskAssessmentNumber riskAssessmentNumber);
}
