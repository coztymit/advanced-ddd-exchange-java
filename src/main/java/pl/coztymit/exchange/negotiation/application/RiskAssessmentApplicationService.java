package pl.coztymit.exchange.negotiation.application;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessment;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessmentNumber;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessmentRepository;
import pl.coztymit.exchange.negotiation.domain.risk.RiskLevel;

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

}
