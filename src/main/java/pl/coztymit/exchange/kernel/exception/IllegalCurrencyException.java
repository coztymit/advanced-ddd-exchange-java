package pl.coztymit.exchange.kernel.exception;

public class IllegalCurrencyException extends RuntimeException{
    public IllegalCurrencyException(String message) {
        super(message);
    }
}
