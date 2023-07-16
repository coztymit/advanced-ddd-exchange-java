package pl.coztymit.exchange.negotiation.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coztymit.exchange.negotiation.application.ChangeRiskAssessmentRiskLevelStatus;
import pl.coztymit.exchange.negotiation.application.RiskAssessmentApplicationService;

import java.util.UUID;

@RestController
@RequestMapping("/riskassessment")
public class RiskAssessmentController {

    @Autowired
    private RiskAssessmentApplicationService riskAssessmentApplicationService;

    @PostMapping("/{riskAssessmentNumber}/{riskLevel}")
    public ChangeRiskAssessmentRiskLevelStatus changeRiskAssessmentRiskLevel(@PathVariable UUID riskAssessmentNumber, @PathVariable String riskLevel) {
       return riskAssessmentApplicationService.changeRiskAssessmentRiskLevel(riskAssessmentNumber, riskLevel);
    }

}
