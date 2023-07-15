package pl.coztymit.exchange.currency.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_pairs")
public class CurrencyPair {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "currency_pair_id"))
    @EmbeddedId
    private CurrencyPairId currencyPairId;

    @AttributeOverride(name = "value", column = @Column(name = "base_currency"))
    private Currency baseCurrency;

    @AttributeOverride(name = "value", column = @Column(name = "target_currency"))
    private Currency targetCurrency;

    @AttributeOverrides({
            @AttributeOverride(name = "baseRate", column = @Column(name = "base_rate")),
            @AttributeOverride(name = "adjustedRate", column = @Column(name = "adjusted_rate"))
    })
    private ExchangeRate exchangeRate;

    @AttributeOverride(name = "status", column = @Column(name = "status"))
    private Status status;

    private CurrencyPair() {

    }

    CurrencyPair (CurrencyPairId currencyPairId, Currency baseCurrency, Currency targetCurrency, ExchangeRate exchangeRate) {
        this.currencyPairId = currencyPairId;
        this.exchangeRate = exchangeRate;
        this.status = Status.ACTIVE;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public void adjustExchangeRate(BigDecimal adjustedRate){
        this.exchangeRate = this.exchangeRate.adjust(adjustedRate);
    }

    public void deactivate(){
        this.status = Status.INACTIVE;
    }

    public CurrencyPairId currencyPairId() {
        return currencyPairId;
    }
}
