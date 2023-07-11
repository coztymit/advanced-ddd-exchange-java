package pl.coztymit.exchange.currency.domain;

import jakarta.persistence.Embeddable;

@Embeddable
class Status {
    public static final Status ACTIVE = new Status("ACTIVE");
    public static final Status INACTIVE = new Status("INACTIVE");

    private String status;

    private Status() {
    }

    private Status(String status) {
        this.status = status;
    }
}
