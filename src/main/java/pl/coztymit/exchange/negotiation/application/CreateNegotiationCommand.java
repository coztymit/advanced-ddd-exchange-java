package pl.coztymit.exchange.negotiation.application;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;

public record CreateNegotiationCommand(IdentityId identityId, Currency baseCurrency, Currency targetCurrency, BigDecimal proposedExchangeAmount, String proposedExchangeCurrency, BigDecimal proposedRate) {

}
