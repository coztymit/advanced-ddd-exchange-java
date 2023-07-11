package pl.coztymit.exchange.account.application;

public class TransferFundsStatus {
    public static final TransferFundsStatus TRANSFER_SUCCESS = new TransferFundsStatus("Transfer success");
    public static final TransferFundsStatus INSUFFICIENT_FUNDS = new TransferFundsStatus("Insufficient funds");
    public static final TransferFundsStatus WALLETS_LIMIT_EXCEEDED = new TransferFundsStatus("Wallets limit exceeded");
    public static final TransferFundsStatus TRANSACTION_LIMIT_EXCEEDED = new TransferFundsStatus("Transaction limit exceeded");
    public static final TransferFundsStatus ACCOUNT_NOT_FOUND = new TransferFundsStatus("Account not found");

    private String status;

    public TransferFundsStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
