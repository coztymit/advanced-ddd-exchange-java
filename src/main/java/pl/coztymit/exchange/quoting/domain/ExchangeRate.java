package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

@Embeddable
public class ExchangeRate {
    private Currency currencyToSell;
    private Currency currencyToBuy;
    private BigDecimal rate;

    //to sell PLN
    // TO buy EUR
    // 1 PLN = 0.22 EUR

    //to sell EUR
    //to buy PLN
    // 1 EUR = 4.55 PLN
    private ExchangeRate(){

    }

    public ExchangeRate(Currency currencyToSell, Currency currencyToBuy, BigDecimal rate) {
        if(currencyToSell.equals(currencyToBuy)){
            throw new RuntimeException("Currencies are the same");
        }
        this.currencyToSell = currencyToSell;
        this.currencyToBuy = currencyToBuy;
        this.rate = rate;
    }

    public boolean isMoreFavorableThan(ExchangeRate rate) {
        if (!this.currencyToBuy.equals(rate.currencyToBuy)) {
            throw new RuntimeException("Different currencies");
        }
        if (!this.currencyToSell.equals(rate.currencyToSell)) {
            throw new RuntimeException("Different currencies");
        }
        return this.rate.compareTo(rate.rate) > 0;
    }

    public ExchangeRate applyDiscount(DiscountLevel discountLevel) {
        return new ExchangeRate(
                currencyToSell,
                currencyToBuy,
                discountLevel.calculate(rate)
        );
    }

    public Money exchange(Money moneyToExchange) {
        if (moneyToExchange.theSameCurrency(currencyToSell)) {

            return new Money(moneyToExchange.multiply(rate), currencyToBuy);
        }
        return new Money(moneyToExchange.div(rate), currencyToSell);
    }
}
