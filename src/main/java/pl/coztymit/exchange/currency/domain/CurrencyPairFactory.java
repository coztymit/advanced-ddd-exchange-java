package pl.coztymit.exchange.currency.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.exception.CurrencyPairNotSupportedException;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CurrencyPairFactory {

    @Autowired
    private BaseCurrencyPairRate baseCurrencyPairRate;

    public CurrencyPair create(Currency baseCurrency, Currency targetCurrency) throws CurrencyPairNotSupportedException {
        Optional<ExchangeRate> optionalExchangeRate = baseCurrencyPairRate.baseRateFor(baseCurrency, targetCurrency);
        CurrencyPairId pairId = CurrencyPairId.generate();
        ExchangeRate exchangeRate = optionalExchangeRate.orElseThrow(() -> new CurrencyPairNotSupportedException(baseCurrency, targetCurrency));
        return new CurrencyPair(pairId, baseCurrency, targetCurrency, exchangeRate);
    }

    public CurrencyPair create(BigDecimal adjustedRate, Currency baseCurrency, Currency targetCurrency) throws CurrencyPairNotSupportedException {
        Optional<ExchangeRate> optionalExchangeRate = baseCurrencyPairRate.baseRateFor(baseCurrency, targetCurrency);
        ExchangeRate exchangeRate = optionalExchangeRate.orElseThrow(() -> new CurrencyPairNotSupportedException(baseCurrency, targetCurrency));
        exchangeRate = exchangeRate.adjust(adjustedRate);
        CurrencyPairId pairId = CurrencyPairId.generate();
        return new CurrencyPair(pairId, baseCurrency, targetCurrency, exchangeRate);
    }
}
