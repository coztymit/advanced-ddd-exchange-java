package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Currency;

@Embeddable
public class BestExchangeRate {
    private Currency currencyToSell;
    private Currency currencyToBuy;
    private Rate rate;

    private BestExchangeRate(){
    }

    public BestExchangeRate(Currency currencyToSell, Currency currencyToBuy, Rate rate) {
        if(currencyToSell.equals(currencyToBuy)){
            throw new RuntimeException("Currencies are the same");
        }
        this.currencyToSell = currencyToSell;
        this.currencyToBuy = currencyToBuy;
        this.rate = rate;
    }

    public boolean isMoreFavorableThan(BestExchangeRate rate) {
        if (!this.currencyToBuy.equals(rate.currencyToBuy)) {
            throw new RuntimeException("Different currencies");
        }
        if (!this.currencyToSell.equals(rate.currencyToSell)) {
            throw new RuntimeException("Different currencies");
        }
        return this.rate.compareTo(rate.rate) > 0;
    }

    public BestExchangeRate applyDiscount(DiscountLevel discountLevel) {
        return new BestExchangeRate(
                currencyToSell,
                currencyToBuy,
                discountLevel.calculate(rate)
        );
    }

    public MoneyExchanged exchange(MoneyToExchange moneyToExchange) {
        if (moneyToExchange.theSameCurrency(currencyToSell)) {
            return moneyToExchange.multiplyWithChangeCurrency(rate, currencyToBuy);
        }
        return moneyToExchange.divWithChangeCurrency(rate, currencyToSell);
    }

    public boolean equals(ExchangeRate rate) {

        if (rate == null) return false;

        return rate.equalsRate(this.rate);
    }

}
