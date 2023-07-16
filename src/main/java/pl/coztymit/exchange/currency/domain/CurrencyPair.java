package pl.coztymit.exchange.currency.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairDeactivated;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairDomainEventBus;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairExchangeRateAdjusted;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.function.Function;

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

    public void adjustExchangeRate(BigDecimal adjustedRate, CurrencyPairDomainEventBus currencyPairDomainEventBus){
        this.exchangeRate = this.exchangeRate.adjust(adjustedRate);
        currencyPairDomainEventBus.post(new CurrencyPairExchangeRateAdjusted(currencyPairId, baseCurrency, targetCurrency, adjustedRate));
    }

    public void deactivate(CurrencyPairDomainEventBus eventBus){
        this.status = Status.INACTIVE;
        eventBus.post(new CurrencyPairDeactivated(currencyPairId, baseCurrency, targetCurrency));
    }

    public CurrencyPairId currencyPairId() {
        return currencyPairId;
    }

    public BigDecimal baseRate() {
        return exchangeRate.baseRate(Function.identity());
    }
}
