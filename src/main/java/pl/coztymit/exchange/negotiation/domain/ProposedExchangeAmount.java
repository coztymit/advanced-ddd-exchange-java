package pl.coztymit.exchange.negotiation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

@Embeddable
public class ProposedExchangeAmount {
    public static ProposedExchangeAmount ZERO_PLN = new ProposedExchangeAmount(BigDecimal.ZERO);
    @JsonProperty
    private BigDecimal value;
    @JsonProperty
    private Currency currency;

    private ProposedExchangeAmount() {
    }

    public ProposedExchangeAmount(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.value = value;
        currency = Currency.PLN;
    }

    public ProposedExchangeAmount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public boolean isMoreOrEquals(ProposedExchangeAmount money) {
        return this.value.compareTo(money.value) >= 0;
    }

    public boolean theSameCurrency(Currency currency) {
        if (this.currency.equals(currency)) {
            return true;
        }
        return false;
    }

    public Money asMoney() {
        return new Money(value, currency);
    }
}
