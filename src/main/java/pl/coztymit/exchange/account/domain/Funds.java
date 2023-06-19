package pl.coztymit.exchange.account.domain;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.accounting.domain.exception.DifferentCurrenciesException;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;
@Embeddable
public class Funds {
    public static Funds ZERO_PLN = new Funds(BigDecimal.ZERO);
    private Money value;

    private Funds() {
    }
    public Funds(BigDecimal value) {
        if (value.scale() > 2 || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot have more than 2 decimal places");
        }
        this.value = new Money(value, Currency.PLN);
    }

    public Funds(BigDecimal value, Currency currency) {
        if (value.scale() > 2 || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot have more than 2 decimal places");
        }
        this.value = new Money(value, currency);
    }

    public Funds(Money value) {
        if (value.isNegative()) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
    }

    public Funds addFunds(Funds funds) {
        if (!this.value.theSameCurrency(funds.value)) {
            throw new RuntimeException();
        }
        return new Funds(this.value.add(funds.value));
    }

    public Funds withdrawFunds(Funds funds) throws InsufficientFundsException {
        if (!this.value.theSameCurrency(funds.value)) {
            throw new DifferentCurrenciesException();
        }
        if (this.value.lessThan(funds.value)) {
            throw new InsufficientFundsException();
        }
        return new Funds(this.value.sub(funds.value));
    }

    public Funds sumFunds(Funds funds, Funds funds2) {
        if (!funds.value.theSameCurrency(funds2.value)) {
            throw new DifferentCurrenciesException(funds.value.toString(), funds2.value.toString());
        }
        return new Funds(funds.value.add(funds2.value));
    }

    public boolean equals(Funds funds) {
        return this.value.equals(funds.value);
    }

    public boolean lessOrEqualsThan(Funds valueToCompere) {
        if (this.value.compareTo(valueToCompere.value) <= 0) {
            return true;
        }
        return false;
    }

    public boolean greaterOrEqualsThan(Funds valueToCompere) {
        if (this.value.compareTo(valueToCompere.value) >= 0) {
            return true;
        }
        return false;
    }

    boolean isSameCurrency(Currency currency) {
        return this.value.theSameCurrency(currency);
    }

    boolean isSameCurrency(Funds funds) {
        return this.value.theSameCurrency(funds.value);
    }

    public String toString() {
        return this.value.toString();
    }

    public Funds multiply(BigDecimal rate, Currency currency) {
        return new Funds(this.value.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP), currency);
    }
}
