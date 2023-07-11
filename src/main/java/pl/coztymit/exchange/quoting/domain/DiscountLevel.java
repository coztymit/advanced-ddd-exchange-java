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

    public BigDecimal calculate(BigDecimal value) {
        return value.multiply(discount, new MathContext(2, RoundingMode.HALF_UP));
    }


}
