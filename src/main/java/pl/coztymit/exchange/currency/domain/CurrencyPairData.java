package pl.coztymit.exchange.currency.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class CurrencyPairData {
    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal baseExchangeRate;
    private BigDecimal adjustedExchangeRate;
    private UUID currencyPairId;

    private CurrencyPairData() {
    }

    public CurrencyPairData(UUID currencyPairId, String baseCurrency, String targetCurrency, BigDecimal baseExchangeRate, BigDecimal adjustedExchangeRate) {
        this.currencyPairId = currencyPairId;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.baseExchangeRate = baseExchangeRate;
        this.adjustedExchangeRate = adjustedExchangeRate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }


    public UUID getCurrencyPairId() {
        return currencyPairId;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getBaseExchangeRate() {
        return baseExchangeRate;
    }

    public void setBaseExchangeRate(BigDecimal baseExchangeRate) {
        this.baseExchangeRate = baseExchangeRate;
    }

    public BigDecimal getAdjustedExchangeRate() {
        return adjustedExchangeRate;
    }

    public void setAdjustedExchangeRate(BigDecimal adjustedExchangeRate) {
        this.adjustedExchangeRate = adjustedExchangeRate;
    }

    public void setCurrencyPairId(UUID currencyPairId) {
        this.currencyPairId = currencyPairId;
    }
}
