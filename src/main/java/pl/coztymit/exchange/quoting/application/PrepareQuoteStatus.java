package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.quoting.domain.MoneyExchanged;
import pl.coztymit.exchange.quoting.domain.QuoteNumber;

public class PrepareQuoteStatus {

    private static final String QUOTE_EXISTS = new String("QUOTE_EXISTS");
    private static final String QUOTE_EXPIRED = new String("QUOTE_EXPIRED");
    private static final String QUOTE_PREPARED = new String("QUOTE_PREPARED");

    private final String status;
    private final QuoteNumber quoteNumber;
    private MoneyExchanged moneyExchanged;

    private PrepareQuoteStatus(String status) {
        this.status = status;
        this.moneyExchanged = null;
        this.quoteNumber = null;
    }
    private PrepareQuoteStatus(String status, MoneyExchanged moneyExchanged, QuoteNumber quoteNumber) {
        this.status = status;
        this.moneyExchanged = moneyExchanged;
        this.quoteNumber = quoteNumber;
    }

    private PrepareQuoteStatus(String status, QuoteNumber quoteNumber) {
        this.status = status;
        this.quoteNumber = quoteNumber;
    }
    public static PrepareQuoteStatus prepareSuccessStatus(MoneyExchanged moneyToExchange, QuoteNumber quoteNumber){
        return new PrepareQuoteStatus(QUOTE_PREPARED, moneyToExchange, quoteNumber);
    }

    public static PrepareQuoteStatus prepareExistsStatus(QuoteNumber quoteNumber){
        return new PrepareQuoteStatus(QUOTE_EXISTS, quoteNumber);
    }

    public String getStatus() {
        return status;
    }

    public MoneyExchanged getQuotePrice(){
        return moneyExchanged;
    }
    public QuoteNumber getQuoteId(){
        return quoteNumber;
    }
}
