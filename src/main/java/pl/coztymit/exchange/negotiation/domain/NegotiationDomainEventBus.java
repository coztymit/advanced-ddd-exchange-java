package pl.coztymit.exchange.negotiation.domain;

import pl.coztymit.exchange.negotiation.domain.event.NegotiationApproved;
import pl.coztymit.exchange.negotiation.domain.event.NegotiationCreated;

public interface NegotiationDomainEventBus{

    void post(NegotiationCreated event);
    void post(NegotiationApproved event);
}
