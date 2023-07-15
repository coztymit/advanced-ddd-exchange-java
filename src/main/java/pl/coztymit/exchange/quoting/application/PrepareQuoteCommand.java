package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;

public record PrepareQuoteCommand(IdentityId identityId, BigDecimal moneyToExchangeValue, String moneyToExchangeCurrency, String currencyToSell, String currencyToBuy) {
}
