package pl.coztymit.exchange.currency.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class ExchangeRate {
    private BigDecimal baseRate;
    private BigDecimal adjustedRate;

    private ExchangeRate() {
    }

    public ExchangeRate(BigDecimal baseRate) {

        this.baseRate = baseRate;
    }
    ExchangeRate(BigDecimal baseRate, BigDecimal adjustedRate) {
        this.baseRate = baseRate;
        this.adjustedRate = adjustedRate;
    }

    public ExchangeRate adjust(BigDecimal adjustedRate) {
        return new ExchangeRate(this.baseRate, adjustedRate);
    }
}
