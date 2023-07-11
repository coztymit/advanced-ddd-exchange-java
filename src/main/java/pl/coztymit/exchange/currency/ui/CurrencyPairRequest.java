package pl.coztymit.exchange.currency.ui;

import pl.coztymit.exchange.kernel.Currency;

class CurrencyPairRequest {
    private Currency baseCurrency;
    private Currency targetCurrency;

    public CurrencyPairRequest() {
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
}
