package pl.coztymit.exchange.account.domain.exception;

public class TransactionLimitExceededException extends Exception {
    public TransactionLimitExceededException(String message) {
        super(message);
    }
}
