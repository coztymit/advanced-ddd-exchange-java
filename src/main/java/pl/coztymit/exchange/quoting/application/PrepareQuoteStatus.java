package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.kernel.Money;

public class PrepareQuoteStatus {

    public static final PrepareQuoteStatus QUOTE_EXISTS = new PrepareQuoteStatus("QUOTE_EXISTS");
    public static final PrepareQuoteStatus QUOTE_EXPIRED = new PrepareQuoteStatus("QUOTE_EXPIRED");

    private final String status;
    private final Money quote;


    private PrepareQuoteStatus(String status) {
        this.status = status;
        this.quote = null;
    }
    private PrepareQuoteStatus(String status, Money value) {
        this.status = status;
        this.quote = value;
    }

    public static PrepareQuoteStatus prepareSuccessStatus(Money quote){
        return new PrepareQuoteStatus("QUOTE_PREPARED", quote);
    }

    public String getStatus() {
        return status;
    }
    public Money getQuote(){
        return quote;
    }
}
