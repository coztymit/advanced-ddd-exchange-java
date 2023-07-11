package pl.coztymit.exchange.quoting.domain;


import jakarta.persistence.*;
import pl.coztymit.exchange.account.domain.AccountId;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

//Oferta
@Entity
@Table(name = "quotes")
public class Quote {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "quote_id"))
    @EmbeddedId
    private QuoteId quoteId;

    @AttributeOverride(name = "value", column = @Column(name = "trader_number"))
    private TraderNumber traderNumber;

    @AttributeOverride(name = "expirationDate", column = @Column(name = "expiration_date"))
    private ExpirationDate expirationDate;

    @AttributeOverrides({
            @AttributeOverride(name = "currencyToSell.value", column = @Column(name = "exchange_rate_currency_to_sell")),
            @AttributeOverride(name = "currencyToBuy.value", column = @Column(name = "exchange_rate_currency_to_buy")),
            @AttributeOverride(name = "rate", column = @Column(name = "exchange_rate"))

    })
    private ExchangeRate bestExchangeRate;

    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "to_exchange_value")),
            @AttributeOverride(name = "currency.value", column = @Column(name = "to_exchange_currency"))
    })
    private Money moneyToExchange;

    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "exchanged_value")),
            @AttributeOverride(name = "currency.value", column = @Column(name = "exchanged_currency"))
    })
    private Money moneyExchanged;

    @AttributeOverride(name = "status", column = @Column(name = "status"))
    private QuoteStatus quoteStatus;

    private Quote(){
    }

    public Quote(TraderNumber traderNumber, ExchangeRate bestExchangeRate, Money moneyToExchange, Money moneyExchanged, QuoteExpirationDatePolicy quoteExpirationDatePolicy) {
        this.quoteId = QuoteId.generate();
        this.traderNumber = traderNumber;
        this.bestExchangeRate = bestExchangeRate;
        this.moneyToExchange = moneyToExchange;
        this.moneyExchanged = moneyExchanged;
        this.quoteStatus = QuoteStatus.PREPARED;
        this.expirationDate = quoteExpirationDatePolicy.generateExpirationDate();
    }

    public void accept() {
        if (this.quoteStatus != QuoteStatus.EXPIRED && this.quoteStatus != QuoteStatus.REJECTED)
        this.quoteStatus = QuoteStatus.ACCEPTED;
    }

    public void reject() {
        if (this.quoteStatus != QuoteStatus.EXPIRED && this.quoteStatus != QuoteStatus.ACCEPTED)
        this.quoteStatus = QuoteStatus.REJECTED;
    }

    public void expire() {
        if (this.quoteStatus != QuoteStatus.ACCEPTED && this.quoteStatus != QuoteStatus.REJECTED)
        this.quoteStatus = QuoteStatus.EXPIRED;
    }
}
