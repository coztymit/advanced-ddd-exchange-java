package pl.coztymit.exchange.account.ui;

import java.math.BigDecimal;

public class TransferFundRequest {

    private BigDecimal amount;
    private String currency;

    public TransferFundRequest(BigDecimal amount, String currency) {

        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
