package pl.coztymit.exchange.kernel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.accounting.domain.exception.DifferentCurrenciesException;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Embeddable
public class Money {

    public static Money ZERO_PLN = new Money(BigDecimal.ZERO);
    @JsonProperty
    private BigDecimal value;
    @JsonProperty
    private Currency currency;

    private Money() {
    }
    public Money(BigDecimal value) {
        this.value = value;
        currency = Currency.PLN;
    }

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money money) {
        if (!this.currency.equals(money.currency)) {
            //TODO specify exception
            throw new RuntimeException();
        }
        return new Money(this.value.add(money.value), this.currency);
    }

    public Money sub(Money money) {
        if (!this.currency.equals(money.currency)) {
            //TODO specify exception
            throw new RuntimeException();
        }
        return new Money(this.value.subtract(money.value), this.currency);
    }

    public Money add(Money money, Money money2) {
        if (!money2.currency.equals(money.currency)) {
            throw new DifferentCurrenciesException(money.currency.toString(), money2.currency.toString());
        }
        return new Money(money.value.add(money2.value), this.currency);
    }

    public boolean equals(Money money) {
        if (this == money) return true;
        if (money == null || getClass() != money.getClass()) return false;

        return value.equals(this.value) &&
                currency.equals(money.currency);
    }


    public boolean lessThan(Money valueToCompere) {
        if (this.value.compareTo(valueToCompere.value) < 0) {
            return true;
        }
        return false;
    }

    public boolean theSameCurrency(Money money) {
        if (this.currency.equals(money.currency)) {
            return true;
        }
        return false;
    }
    public boolean theSameCurrency(Currency currency) {
        if (this.currency.equals(currency)) {
            return true;
        }
        return false;
    }

    public int compareTo(Money money) {
        return this.value.compareTo(money.value);
    }
    public String toString() {
        return value.toString() + " currency:" + this.currency.toString();
    }

    public BigDecimal multiply(BigDecimal rate) {
        return this.value.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal div(BigDecimal rate) {
        return this.value.divide(rate, 2, RoundingMode.HALF_UP);
    }

    public boolean isNegative() {
        return this.value.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isMoreOrEquals(Money money) {
        return this.value.compareTo(money.value) >= 0;
    }
}
