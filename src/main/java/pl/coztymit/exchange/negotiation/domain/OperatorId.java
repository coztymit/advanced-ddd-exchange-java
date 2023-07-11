package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class OperatorId {

    private UUID uuid;

    private OperatorId(){
    }

    public OperatorId(UUID uuid) {
        this.uuid = uuid;
    }

    public static OperatorId generate(){
        return new OperatorId(UUID.randomUUID());
    }
}
