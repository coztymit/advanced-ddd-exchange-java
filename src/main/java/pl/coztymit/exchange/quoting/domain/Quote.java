package pl.coztymit.exchange.quoting.domain;


import jakarta.persistence.*;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

//Oferta
@Entity
@Table(name = "quotes")
public class Quote {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "quote_id"))
    @EmbeddedId
    private QuoteNumber quoteNumber;

    @AttributeOverride(name = "identityId.uuid", column = @Column(name = "requester_identity_id"))
    private Requester requester;

    @AttributeOverride(name = "expirationDate", column = @Column(name = "expiration_date"))
    private ExpirationDate expirationDate;

    @AttributeOverrides({
            @AttributeOverride(name = "currencyToSell.value", column = @Column(name = "best_exchange_rate_currency_to_sell")),
            @AttributeOverride(name = "currencyToBuy.value", column = @Column(name = "best_exchange_rate_currency_to_buy")),
            @AttributeOverride(name = "rate.value", column = @Column(name = "best_exchange_rate"))

    })
    private BestExchangeRate bestExchangeRate;

    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "to_exchange_value")),
            @AttributeOverride(name = "currency.value", column = @Column(name = "to_exchange_currency"))
    })
    private MoneyToExchange moneyToExchange;

    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "exchanged_value")),
            @AttributeOverride(name = "currency.value", column = @Column(name = "exchanged_currency"))
    })
    private MoneyExchanged moneyExchanged;

    @AttributeOverride(name = "status", column = @Column(name = "status"))
    private QuoteStatus quoteStatus;

    private Quote(){
    }

    public Quote(Requester requester, BestExchangeRate bestExchangeRate, MoneyToExchange moneyToExchange, MoneyExchanged moneyExchanged, QuoteExpirationDatePolicy quoteExpirationDatePolicy) {
        this.quoteNumber = QuoteNumber.generate();
        this.requester = requester;
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

    public QuoteNumber getQuoteId() {
        return quoteNumber;
    }
}
