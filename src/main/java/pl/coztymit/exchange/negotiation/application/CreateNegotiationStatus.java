package pl.coztymit.exchange.negotiation.application;

public class CreateNegotiationStatus {
    public static final CreateNegotiationStatus PENDING = new CreateNegotiationStatus("PENDING");
    public static final CreateNegotiationStatus APPROVED = new CreateNegotiationStatus("APPROVED");
    public static final CreateNegotiationStatus REJECTED_TOO_SMALL_AMOUNT = new CreateNegotiationStatus("REJECTED_TOO_SMALL_AMOUNT");
    public static final CreateNegotiationStatus REJECTED = new CreateNegotiationStatus("REJECTED");
    public static final CreateNegotiationStatus ALREADY_EXISTS = new CreateNegotiationStatus("ALREADY_EXISTS");

    private String status;

    private CreateNegotiationStatus(String status) {
        this.status = status;
    }
}
