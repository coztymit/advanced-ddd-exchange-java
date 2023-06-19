package pl.coztymit.exchange.account.ui;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public class FundsToDeposit {
    private BigDecimal value;
    private String currency;

    public FundsToDeposit(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
