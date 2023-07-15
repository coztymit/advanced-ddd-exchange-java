package pl.coztymit.exchange.negotiation.application;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

public record CreateNegotiationCommand(IdentityId identityId, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount, BigDecimal proposedRate) {

}
