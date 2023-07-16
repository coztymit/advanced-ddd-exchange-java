package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.IdentityId;

@Embeddable
public class Requester {
    private IdentityId identityId;

    private Requester(){
    }

    public Requester(IdentityId identityId) {
        this.identityId = identityId;
    }

    public IdentityId identityId() {
        return identityId;
    }
}
