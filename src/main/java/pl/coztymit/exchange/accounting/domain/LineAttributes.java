package pl.coztymit.exchange.accounting.domain;

import java.math.BigDecimal;

public interface LineAttributes {
    String productNumber();

    BigDecimal productValue();

    String valueCurrency();
}
