package pl.coztymit.exchange.negotiation.application;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.kernel.Money;

public record FindAcceptedActiveNegotiationRateCommand (IdentityId identityId, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount) {
}
