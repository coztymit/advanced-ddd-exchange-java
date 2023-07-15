package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Currency;

@Embeddable
public class ExchangeRate {
    private Currency currencyToSell;
    private Currency currencyToBuy;
    private Rate rate;

    private ExchangeRate(){
    }

    public ExchangeRate(Currency currencyToSell, Currency currencyToBuy, Rate rate) {
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

    public MoneyExchanged exchange(MoneyToExchange moneyToExchange) {
        if (moneyToExchange.theSameCurrency(currencyToSell)) {
            return moneyToExchange.multiplyWithChangeCurrency(rate, currencyToBuy);
        }
        return moneyToExchange.divWithChangeCurrency(rate, currencyToSell);
    }

    public Currency getCurrencyToSell() {
        return currencyToSell;
    }

    public Currency getCurrencyToBuy() {
        return currencyToBuy;
    }

    public Rate getRate() {
        return rate;
    }

    public boolean equalsRate(Rate rate) {
        if (this.rate == rate) return true;
        if (rate == null) return false;
        return this.rate.equals(rate);
    }
}
