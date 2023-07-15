package pl.coztymit.exchange.kernel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.exception.IllegalCurrencyException;

@Embeddable
public class Currency {
    @JsonProperty
    private String value;
    public static Currency PLN = new Currency("PLN");
    public static Currency EUR = new Currency("EUR");

    private Currency() {
    }
    public Currency(String value) {
        if (!value.matches("[A-Z]{3}")) {
            throw new IllegalCurrencyException("Incorrect currency value");
        }
        this.value = value;
    }

    public boolean equals(Currency currency) {
        if (this == currency) return true;
        if (currency == null || getClass() != currency.getClass()) return false;

        return this.value.equals(currency.value);
    }

    public String toString() {
        return value;
    }
}
