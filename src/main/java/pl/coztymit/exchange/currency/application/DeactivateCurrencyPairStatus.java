package pl.coztymit.exchange.currency.application;

public class DeactivateCurrencyPairStatus {
    public final static String SUCCESS = "SUCCESS";
    public final static String CURRENCY_PAIR_NOT_FOUND = "CURRENCY_PAIR_NOT_FOUND";

    private String status;

    private DeactivateCurrencyPairStatus(String status) {
        this.status = status;
    }

    public static DeactivateCurrencyPairStatus createSuccessStatus() {
        return new DeactivateCurrencyPairStatus(SUCCESS);
    }

    public static DeactivateCurrencyPairStatus createCurrencyPairNotFoundStatus() {
        return new DeactivateCurrencyPairStatus(CURRENCY_PAIR_NOT_FOUND);
    }

    public String getStatus() {
        return status;
    }

    public String status() {
        return status;
    }
}
