package pl.coztymit.exchange.account.domain.exception;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String message) {
        super(message);
    }
    public WalletNotFoundException() {
        super("Wallet not found");
    }
}
