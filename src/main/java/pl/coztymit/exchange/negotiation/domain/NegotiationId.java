package pl.coztymit.exchange.negotiation.domain;

import pl.coztymit.exchange.quoting.domain.QuoteId;

import java.util.UUID;

public class NegotiationId {
    private UUID uuid;

    private NegotiationId(){
    }

    public NegotiationId(UUID uuid) {
        this.uuid = uuid;
    }

    public static NegotiationId generate(){
        return new NegotiationId(UUID.randomUUID());
    }
}
