package pl.coztymit.exchange.currency.application;

import pl.coztymit.exchange.currency.domain.CurrencyPairId;

public class AddCurrencyPairStatus {
    public final static String SUCCESS = "SUCCESS";
    public final static String CURRENCY_PAIR_ALREADY_EXISTS = "CURRENCY_PAIR_ALREADY_EXISTS";

    private String status;
    private CurrencyPairId currencyPairId;

    private AddCurrencyPairStatus(String status, CurrencyPairId currencyPairId) {
        this.status = status;
        this.currencyPairId = currencyPairId;
    }
    private AddCurrencyPairStatus(String status) {
        this.status = status;
    }

    public static AddCurrencyPairStatus createSuccessStatus(CurrencyPairId currencyPairId) {
        return new AddCurrencyPairStatus(SUCCESS, currencyPairId);
    }

    public static AddCurrencyPairStatus createCurrencyPairAlreadyExistsStatus() {
        return new AddCurrencyPairStatus(CURRENCY_PAIR_ALREADY_EXISTS);
    }

    public static AddCurrencyPairStatus createFailureStatus(String message) {
        return new AddCurrencyPairStatus(message);
    }

    public String status() {
        return status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CurrencyPairId getCurrencyPairId() {
        return currencyPairId;
    }

    public void setCurrencyPairId(CurrencyPairId currencyPairId) {
        this.currencyPairId = currencyPairId;
    }

    public CurrencyPairId currencyPairId() {
        return currencyPairId;
    }
}
