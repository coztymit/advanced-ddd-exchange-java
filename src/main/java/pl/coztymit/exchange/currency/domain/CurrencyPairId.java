package pl.coztymit.exchange.currency.domain;

import pl.coztymit.exchange.quoting.domain.QuoteId;

import java.util.UUID;

public class CurrencyPairId {
    private UUID uuid;

    private CurrencyPairId(){
    }

    public CurrencyPairId(UUID uuid) {
        this.uuid = uuid;
    }

    public static CurrencyPairId generate(){
        return new CurrencyPairId(UUID.randomUUID());
    }
}
