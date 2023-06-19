package pl.coztymit.exchange.accounting.domain.exception;

public class DifferentCurrenciesException extends RuntimeException {
    public DifferentCurrenciesException(String firstCurrency, String secoundCurrency) {
        super("Different currencies: " + firstCurrency + " and " + secoundCurrency);
    }
    public DifferentCurrenciesException() {
        super("Different currencies");
    }
}
