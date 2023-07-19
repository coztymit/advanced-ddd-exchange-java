package pl.coztymit.exchange.identity.domain;

import pl.coztymit.exchange.identity.domain.event.IdentityActivated;
import pl.coztymit.exchange.identity.domain.event.IdentityCreated;
import pl.coztymit.exchange.identity.domain.event.IdentityDeactivated;

public interface IdentityDomainEventBus {
    void post(IdentityCreated event);
    void post(IdentityDeactivated event);
    void post(IdentityActivated event);
}
