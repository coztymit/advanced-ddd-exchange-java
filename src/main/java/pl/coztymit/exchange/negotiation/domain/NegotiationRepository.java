package pl.coztymit.exchange.negotiation.domain;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.exception.NegotiationNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

public interface NegotiationRepository {
    void save(Negotiation negotiation);
    Negotiation findById(NegotiationId id) throws NegotiationNotFoundException;
    boolean alreadyExistsActiveNegotiationForNegotiator(Negotiator negotiator, Currency baseCurrency, Currency targetCurrency, BigDecimal rate, ProposedExchangeAmount proposedExchangeAmount );
    BigDecimal findApprovedRateById(NegotiationId negotiationId) throws NegotiationNotFoundException;
    Optional<BigDecimal> findAcceptedActiveNegotiation(Negotiator negotiator, Currency baseCurrency, Currency targetCurrency, ProposedExchangeAmount proposedExchangeAmount);
}
