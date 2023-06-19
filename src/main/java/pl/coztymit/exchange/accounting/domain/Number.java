package pl.coztymit.exchange.accounting.domain;

import java.util.UUID;

public class Number {
    private UUID number;

    public Number() {
        number = UUID.randomUUID();
    }

    public Number(String number) {
        this.number = UUID.fromString(number);
    }

    public String toString() {
        return number.toString();
    }
}
