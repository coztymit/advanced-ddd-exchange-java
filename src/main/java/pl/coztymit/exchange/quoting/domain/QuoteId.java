package pl.coztymit.exchange.quoting.domain;

import java.util.UUID;

public class QuoteId {
    private UUID uuid;

    private QuoteId(){
    }

    public QuoteId(UUID uuid) {
        this.uuid = uuid;
    }

    public static QuoteId generate(){
        return new QuoteId(UUID.randomUUID());
    }
}
