package pl.coztymit.exchange.negotiation.application;

public class CreateNegotiationStatus {
    public static final CreateNegotiationStatus PENDING = new CreateNegotiationStatus("PENDING");
    public static final CreateNegotiationStatus APPROVED = new CreateNegotiationStatus("APPROVED");
    public static final CreateNegotiationStatus REJECTED_TOO_SMALL_AMOUNT = new CreateNegotiationStatus("REJECTED_TOO_SMALL_AMOUNT");
    public static final CreateNegotiationStatus REJECTED = new CreateNegotiationStatus("REJECTED");
    public static final CreateNegotiationStatus ALREADY_EXISTS = new CreateNegotiationStatus("ALREADY_EXISTS");
    public static final CreateNegotiationStatus CURRENCY_PAIR_NOT_SUPPORTED = new CreateNegotiationStatus("CURRENCY_PAIR_NOT_SUPPORTED");

    private String status;

    private CreateNegotiationStatus() {
    }

    private CreateNegotiationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
