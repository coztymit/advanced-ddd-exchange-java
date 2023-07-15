package pl.coztymit.exchange.currency.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CurrencyPairId {

    @JsonProperty
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
