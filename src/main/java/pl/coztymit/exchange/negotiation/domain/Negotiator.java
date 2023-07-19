package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.function.Function;

@Embeddable
public class Negotiator {
    private IdentityId identityId;

    private Negotiator(){

    }

    public Negotiator(IdentityId identityId) {
        this.identityId = identityId;
    }

    public <R> R identity(Function<IdentityId, R> converter) {
        return converter.apply(this.identityId);
    }
}
