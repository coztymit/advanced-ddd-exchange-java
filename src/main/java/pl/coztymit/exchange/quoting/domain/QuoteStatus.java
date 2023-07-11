package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class QuoteStatus {
    public static final QuoteStatus EXPIRED = new QuoteStatus("EXPIRED");
    public static final QuoteStatus ACCEPTED = new QuoteStatus("ACCEPTED");
    public static final QuoteStatus REJECTED = new QuoteStatus("REJECTED");
    public static final QuoteStatus PREPARED = new QuoteStatus("PREPARED");

    private String status;

    private QuoteStatus(){
    }
    private QuoteStatus(String status) {
        this.status =  status;
    }
}
