package pl.coztymit.exchange.currency.application;

public class AddCurrencyPairWithRateResponse {
    private final static String SUCCESS = "SUCCESS";
    private final static String CURRENCY_PAIR_NOT_FOUND = "CURRENCY_PAIR_NOT_FOUND";
    private final static String CURRENCY_PAIR_ALREADY_EXISTS = "CURRENCY_PAIR_ALREADY_EXISTS";
    private final static String CURRENCY_PAIR_NOT_SUPPORTED = "CURRENCY_PAIR_NOT_SUPPORTED";

    private String status;

    private AddCurrencyPairWithRateResponse(String status) {
        this.status = status;
    }

    public static AddCurrencyPairWithRateResponse createSuccessStatus() {
        return new AddCurrencyPairWithRateResponse(SUCCESS);
    }

    public static AddCurrencyPairWithRateResponse createNorSupportedStatus() {
        return new AddCurrencyPairWithRateResponse(CURRENCY_PAIR_NOT_SUPPORTED);
    }

    public static AddCurrencyPairWithRateResponse createAlreadyExistsStatus() {
        return new AddCurrencyPairWithRateResponse(CURRENCY_PAIR_ALREADY_EXISTS);
    }

    public static AddCurrencyPairWithRateResponse createCurrencyPairNotFoundStatus() {
        return new AddCurrencyPairWithRateResponse(CURRENCY_PAIR_NOT_FOUND);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
