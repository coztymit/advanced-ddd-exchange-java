package pl.coztymit.exchange.accounting.application;

import pl.coztymit.exchange.accounting.domain.LineAttributes;

import java.math.BigDecimal;

public class InvoiceLine implements LineAttributes {
    private String number;
    private BigDecimal value;
    private String currency;

    public InvoiceLine(BigDecimal productValue, String currency)
    {
        this.number = "randomnumber";
        this.value = productValue;
        this.currency = currency;
    }
    @Override
    public String productNumber() {
        return number;
    }

    @Override
    public BigDecimal productValue() {
        return value;
    }

    @Override
    public String valueCurrency() {
        return currency;
    }
}
