package pl.coztymit.exchange.accounting.domain;

import java.util.UUID;

class PositionBusinessId {

    private UUID number;

    public PositionBusinessId() {
        number = UUID.randomUUID();
    }

    public String toString() {
        return number.toString();
    }
}
