package pl.coztymit.exchange.account.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Status {
    public static final Status ACTIVE = new Status("ACTIVE");
    public static final Status INACTIVE = new Status("INACTIVE");

    private String status;

    private Status() {
    }

    private Status(String status) {
        this.status = status;
    }
}
