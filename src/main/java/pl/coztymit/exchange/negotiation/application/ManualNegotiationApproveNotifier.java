package pl.coztymit.exchange.negotiation.application;

public interface ManualNegotiationApproveNotifier {
    void notifyManualApprovalRequired();
    void notifyNegotiationApproved(String negotiationId);
    void notifyNegotiationRejected(String negotiationId);
}
