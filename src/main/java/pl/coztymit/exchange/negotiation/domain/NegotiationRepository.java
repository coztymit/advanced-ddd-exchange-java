package pl.coztymit.exchange.negotiation.domain;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.exception.NegotiationNotFoundException;

import java.math.BigDecimal;

public interface NegotiationRepository {
    void save(Negotiation negotiation);
    Negotiation findById(NegotiationId id) throws NegotiationNotFoundException;
    boolean alreadyExistsActiveNegotiationForTrader(TraderNumber traderNumber, Currency currency, Currency currency1, BigDecimal bigDecimal, Money money);
    BigDecimal findApprovedRateById(NegotiationId negotiationId) throws NegotiationNotFoundException;
    BigDecimal findAcceptedActiveNegotiation(TraderNumber traderNumber, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount) throws NegotiationNotFoundException;
}
