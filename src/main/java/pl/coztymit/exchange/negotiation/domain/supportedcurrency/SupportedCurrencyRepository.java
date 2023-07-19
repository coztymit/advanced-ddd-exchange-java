package pl.coztymit.exchange.negotiation.domain.supportedcurrency;

import pl.coztymit.exchange.kernel.Currency;

import java.util.Optional;

public interface SupportedCurrencyRepository {
    void save(SupportedCurrency supportedCurrency);
    Optional<SupportedCurrency> findByCurrency(Currency baseCurrency, Currency targetCurrency);
    Optional<SupportedCurrency> findActiveByCurrency(Currency baseCurrency, Currency targetCurrency);
}
