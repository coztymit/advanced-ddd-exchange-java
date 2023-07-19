package pl.coztymit.exchange.currency.domain.event;

import pl.coztymit.exchange.kernel.Currency;

public record CurrencyPairActivated (Currency baseCurrency, Currency targetCurrency){
}
