package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Status {
    public static final Status PENDING = new Status("PENDING");
    public static final Status APPROVED = new Status("APPROVED");
    public static final Status REJECTED_TOO_SMALL_AMOUNT = new Status("REJECTED_TOO_SMALL_AMOUNT");
    public static final Status REJECTED = new Status("REJECTED");
    public static final Status EXPIRED = new Status("EXPIRED");

    private String status;

    private Status() {
    }

    private Status(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return this.equals(APPROVED);
    }
}
