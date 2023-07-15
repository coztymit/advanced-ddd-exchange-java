package pl.coztymit.exchange.negotiation.ui;

import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;

public record FindAcceptedNegotiationRequest (IdentityId identityId, String baseCurrency, String targetCurrency, BigDecimal proposedExchangeAmount, String proposedExchangeCurrency){
}
