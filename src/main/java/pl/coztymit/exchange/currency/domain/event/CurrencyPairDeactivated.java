package pl.coztymit.exchange.currency.domain.event;

import pl.coztymit.exchange.currency.domain.CurrencyPairId;
import pl.coztymit.exchange.kernel.Currency;

public record CurrencyPairDeactivated (CurrencyPairId currencyPairId, Currency baseCurrency, Currency targetCurrency){
}
