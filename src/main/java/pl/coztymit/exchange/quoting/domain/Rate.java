package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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

    public Rate multiply(BigDecimal value){
        return new Rate(this.value.multiply(value, new MathContext(2, RoundingMode.HALF_UP)));
    }

    public BigDecimal multiplyToBigDecimal(BigDecimal value){
        return this.value.multiply(value).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal divToBigDecimal(BigDecimal value){
        return value.divide(this.value, 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
    }

    public int compareTo(Rate rate) {
        return this.value.compareTo(rate.value);
    }

    public boolean equals(Rate rate) {
        return this.value.equals(rate.value);
    }
}
