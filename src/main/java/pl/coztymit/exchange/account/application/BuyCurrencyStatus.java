package pl.coztymit.exchange.account.application;

public class BuyCurrencyStatus {
    public static final BuyCurrencyStatus INSUFFICIENT_FUNDS =  new BuyCurrencyStatus("Insufficient funds");
    public static final BuyCurrencyStatus BUY_SUCCESS =  new BuyCurrencyStatus("Buy success");
    public static final BuyCurrencyStatus ACCOUNT_NOT_FOUND = new BuyCurrencyStatus("Account not found");
    private String status;

    public BuyCurrencyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
