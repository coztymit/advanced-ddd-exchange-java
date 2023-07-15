package pl.coztymit.exchange.currency.application;

public class UpdateCurrencyPairRateStatus {
    public final static String SUCCESS = "SUCCESS";
    public final static String CURRENCY_PAIR_NOT_FOUND = "CURRENCY_PAIR_NOT_FOUND";

    private String status;

    private UpdateCurrencyPairRateStatus(String status) {
        this.status = status;
    }

    public static UpdateCurrencyPairRateStatus createSuccessStatus() {
        return new UpdateCurrencyPairRateStatus(SUCCESS);
    }

    public static UpdateCurrencyPairRateStatus createCurrencyPairNotFoundStatus() {
        return new UpdateCurrencyPairRateStatus(CURRENCY_PAIR_NOT_FOUND);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }
}
