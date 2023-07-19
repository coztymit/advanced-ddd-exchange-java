package pl.coztymit.exchange.quoting.domain;

import java.math.BigDecimal;

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
