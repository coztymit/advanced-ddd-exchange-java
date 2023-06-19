package pl.coztymit.exchange.account.domain.exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}
