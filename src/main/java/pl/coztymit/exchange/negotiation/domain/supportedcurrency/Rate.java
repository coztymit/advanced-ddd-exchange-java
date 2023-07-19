package pl.coztymit.exchange.negotiation.domain.supportedcurrency;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.function.Function;

@Embeddable
public class Rate {

    private BigDecimal value;

    private Rate(){
    }

    public Rate(BigDecimal value) {
        if(value == null || value.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Rate cannot be null or negative");
        }
        this.value = value;
    }

    public <R> R value(Function<BigDecimal, R> converter) {
        return converter.apply(this.value);
    }
}
