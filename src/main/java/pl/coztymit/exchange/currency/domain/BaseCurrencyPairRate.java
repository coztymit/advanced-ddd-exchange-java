package pl.coztymit.exchange.currency.domain;

import pl.coztymit.exchange.kernel.Currency;

import java.util.Optional;

public interface BaseCurrencyPairRate {

    Optional<ExchangeRate> baseRateFor(Currency baseCurrency, Currency targetCurrency);
}
