package pl.coztymit.exchange.negotiation.domain;

public class AutomaticNegotiationStatus {
    public static final AutomaticNegotiationStatus PENDING = new AutomaticNegotiationStatus("PENDING");
    public static final AutomaticNegotiationStatus APPROVED = new AutomaticNegotiationStatus("APPROVED");
    public static final AutomaticNegotiationStatus REJECTED_TOO_SMALL_AMOUNT = new AutomaticNegotiationStatus("REJECTED_TOO_SMALL_AMOUNT");
    public static final AutomaticNegotiationStatus REJECTED = new AutomaticNegotiationStatus("REJECTED");

    private String status;

    private AutomaticNegotiationStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return this.equals(APPROVED);
    }

    public boolean equals(AutomaticNegotiationStatus other) {
        return this.status.equals(other.status);
    }
}
