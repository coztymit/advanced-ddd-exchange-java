package pl.coztymit.exchange.quoting.domain.exception;

public class QuoteNotFoundException extends Exception {
    public QuoteNotFoundException(String message) {
        super(message);
    }
}
