package pl.coztymit.exchange.currency.domain;

import pl.coztymit.exchange.kernel.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyPairRepository {

    void save(CurrencyPair currencyPair);

    Optional<CurrencyPair> findById(CurrencyPairId currencyPairId);

    boolean alreadyExists(Currency baseCurrency, Currency targetCurrency);

    Optional<CurrencyPair> findByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);
    Optional<CurrencyPairData> findDataByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);

    List<CurrencyPairData> findAll();
}
