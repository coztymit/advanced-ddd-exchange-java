package pl.coztymit.exchange.currency.domain.exception;

import pl.coztymit.exchange.kernel.Currency;

public class CurrencyPairNotSupportedException extends Exception {
    public CurrencyPairNotSupportedException(Currency baseCurrency, Currency targetCurrency) {
        super("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
    }
}
