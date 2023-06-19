package pl.coztymit.exchange.account.application.exception;

public class WalletNotFountException extends RuntimeException{
    public WalletNotFountException(String message) {
        super(message);
    }
}
