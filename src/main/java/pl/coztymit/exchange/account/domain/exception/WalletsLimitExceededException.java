package pl.coztymit.exchange.account.domain.exception;

public class WalletsLimitExceededException extends Exception {
    public WalletsLimitExceededException(String message) {
        super(message);
    }
    public WalletsLimitExceededException() {
        super("Wallet limit exceeded");
    }
}
