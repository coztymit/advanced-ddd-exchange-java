package pl.coztymit.exchange.account.domain;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public class ExchangeRate {
    private final Currency currencyToSell;
    private final Currency currencyToBuy;
    private final BigDecimal rate;

    public ExchangeRate(Currency currencyToSell, Currency currencyToBuy, BigDecimal rate) {
        this.currencyToSell = currencyToSell;
        this.currencyToBuy = currencyToBuy;
        this.rate = rate;
    }

    Funds calculate(Funds value) {
        if(!value.isSameCurrency(currencyToBuy)){
            throw new RuntimeException("Different currencies");
        }
        return value.multiply(rate, currencyToSell);
    }
}
