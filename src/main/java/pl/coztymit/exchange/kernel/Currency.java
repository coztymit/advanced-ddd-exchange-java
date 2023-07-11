package pl.coztymit.exchange.kernel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.exception.IllegalCurrencyException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value);
    }

    public String toString() {
        return value;
    }
}
