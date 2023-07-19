package pl.coztymit.exchange.negotiation.application;

public class CreateRiskAssessmentStatus {

    private String status;

    private CreateRiskAssessmentStatus() {
    }

    public CreateRiskAssessmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
