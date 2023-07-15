package pl.coztymit.exchange.negotiation.application;


import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.Optional;

public interface BaseExchangeRateAdvisor {
    Optional<BigDecimal> baseExchangeRate(Currency baseCurrency, Currency targetCurrency);
}
