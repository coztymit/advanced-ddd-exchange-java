package pl.coztymit.exchange.negotiation.application;


public class ChangeRiskAssessmentRiskLevelStatus {
    private String status;

    private ChangeRiskAssessmentRiskLevelStatus() {
    }

    public ChangeRiskAssessmentRiskLevelStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
