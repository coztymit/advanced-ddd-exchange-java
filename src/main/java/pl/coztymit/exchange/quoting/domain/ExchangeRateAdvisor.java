package pl.coztymit.exchange.quoting.domain;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateAdvisor {

    Optional<ExchangeRate> exchangeRate(Currency currencyToSell, Currency currencyToBuy);
}
