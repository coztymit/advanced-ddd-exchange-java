package pl.coztymit.exchange.negotiation.domain.event;

import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.NegotiationId;
import pl.coztymit.exchange.negotiation.domain.Negotiator;

public record NegotiationApproved (NegotiationId getNegotiationId, Negotiator getNegotiator, Money getProposedExchangeAmount){
}
