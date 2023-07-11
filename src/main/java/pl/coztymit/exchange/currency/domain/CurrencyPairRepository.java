package pl.coztymit.exchange.currency.domain;

import pl.coztymit.exchange.kernel.Currency;

import java.util.List;

public interface CurrencyPairRepository {

    void save(CurrencyPair currencyPair);

    CurrencyPair findById(CurrencyPairId currencyPairId);

    boolean alreadyExists(Currency baseCurrency, Currency targetCurrency);

    CurrencyPair findByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);
    CurrencyPairData findDataByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);

    List<CurrencyPairData> findAll();
}
