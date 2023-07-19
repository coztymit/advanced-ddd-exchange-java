package pl.coztymit.exchange.negotiation.domain.supportedcurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.util.UUID;
@Embeddable
public class SupportedCurrencyId {

    @JsonProperty
    private UUID uuid;

    private SupportedCurrencyId(){
    }

    public SupportedCurrencyId(UUID uuid) {
        this.uuid = uuid;
    }

    public static SupportedCurrencyId generate(){
        return new SupportedCurrencyId(UUID.randomUUID());
    }
}
