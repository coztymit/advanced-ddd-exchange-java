package pl.coztymit.exchange.currency.ui;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

class CurrencyPairWithRateRequest {
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal adjustedRate;

    public CurrencyPairWithRateRequest() {
    }

    Currency getBaseCurrency() {
        return baseCurrency;
    }

    private void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    Currency getTargetCurrency() {
        return targetCurrency;
    }

    private void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    BigDecimal getAdjustedRate() {
        return adjustedRate;
    }

    private void setAdjustedRate(BigDecimal adjustedRate) {
        this.adjustedRate = adjustedRate;
    }
}
