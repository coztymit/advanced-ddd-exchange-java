package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.account.application.TransferFundsStatus;

public class AcceptQuoteStatus {
    public static final AcceptQuoteStatus SUCCESS = new AcceptQuoteStatus("SUCCESS");
    public static final AcceptQuoteStatus QUOTE_NOT_FOUND = new AcceptQuoteStatus("QUOTE_NOT_FOUND");

    private String status;

    public AcceptQuoteStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
