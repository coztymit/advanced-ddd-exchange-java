package pl.coztymit.exchange.quoting.ui;

import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;

public record PrepareQuoteRequest(IdentityId identityId, BigDecimal moneyToExchangeValue, String moneyToExchangeCurrency, String currencyToBuy, String currencyToSell) {

}
