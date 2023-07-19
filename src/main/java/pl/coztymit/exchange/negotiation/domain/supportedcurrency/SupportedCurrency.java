package pl.coztymit.exchange.negotiation.domain.supportedcurrency;

import jakarta.persistence.*;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.function.Function;

//To jest encja (nie jest to agregat) która może zostać przeniesiona do stackq query
@Entity
@Table(name = "supported_currencies")
public class SupportedCurrency {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "supported_currency_id"))
    @EmbeddedId
    private SupportedCurrencyId supportedCurrencyId;

    @AttributeOverride(name = "value", column = @Column(name = "base_currency"))
    private Currency baseCurrency;

    @AttributeOverride(name = "value", column = @Column(name = "target_currency"))
    private Currency targetCurrency;

    @AttributeOverride(name = "value", column = @Column(name = "rate"))
    private Rate rate;

    @AttributeOverride(name = "status", column = @Column(name = "status"))
    private Status status;

    private SupportedCurrency() {
    }

    public SupportedCurrency (Currency baseCurrency, Currency targetCurrency, Rate rate) {
        this.supportedCurrencyId = SupportedCurrencyId.generate();
        this.status = Status.ACTIVE;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate.value(Function.identity());
    }

    public void activate(){
        this.status = Status.ACTIVE;
    }
    public void deactivate(){
        this.status = Status.INACTIVE;
    }
}
