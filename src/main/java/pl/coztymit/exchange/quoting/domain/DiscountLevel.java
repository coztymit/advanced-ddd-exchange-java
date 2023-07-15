package pl.coztymit.exchange.quoting.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DiscountLevel {

    public static final DiscountLevel FIVE_PERCENT = new DiscountLevel(BigDecimal.valueOf(0.95));

    private BigDecimal discount;

    private DiscountLevel(BigDecimal discount) {
        this.discount = discount;
    }

    public Rate calculate(Rate value) {
        return value.multiply(discount);
    }


}
