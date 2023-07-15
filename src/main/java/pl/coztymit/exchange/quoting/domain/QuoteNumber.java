package pl.coztymit.exchange.quoting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class QuoteNumber {

    @JsonProperty
    private UUID uuid;

    private QuoteNumber(){
    }

    public QuoteNumber(UUID uuid) {
        this.uuid = uuid;
    }

    public static QuoteNumber generate(){
        return new QuoteNumber(UUID.randomUUID());
    }
}
