package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.IdentityId;

@Embeddable
public class Negotiator {
    private IdentityId identityId;

    private Negotiator(){

    }

    public Negotiator(IdentityId identityId) {
        this.identityId = identityId;
    }
}
