package pl.coztymit.exchange.quoting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;
import java.util.function.Function;

@Embeddable
public class MoneyToExchange {

    @JsonProperty
    private BigDecimal value;
    @JsonProperty
    private Currency currency;

    private MoneyToExchange() {
    }

    public MoneyToExchange(BigDecimal value, Currency currency) {
        if(value.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Value cannot be negative");
        }
        this.value = value;
        this.currency = currency;
    }

    public boolean theSameCurrency(Currency currency) {
        return this.currency.equals(currency);
    }

    public MoneyExchanged multiplyWithChangeCurrency(Rate rate, Currency currency) {
        return new MoneyExchanged(rate.multiplyToBigDecimal(value), currency);
    }

    public MoneyExchanged divWithChangeCurrency(Rate rate, Currency currency) {

        return new MoneyExchanged(rate.divToBigDecimal(value), currency);
    }

    public Money toMoney() {
        return new Money(value, currency);
    }

    public <R> R value(Function<BigDecimal, R> converter) {
        return converter.apply(this.value);
    }

    public <R> R currency(Function<String, R> converter) {
        return converter.apply(this.currency.toString());
    }
}
