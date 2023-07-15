package pl.coztymit.exchange.account.application;

public class DepositFundsStatus {
    public static final DepositFundsStatus TRANSACTION_LIMIT_EXCEEDED = new DepositFundsStatus("Transaction limit exceeded");
    public static final DepositFundsStatus ACCOUNT_NOT_FOUND = new DepositFundsStatus("Account not found");
    public static final DepositFundsStatus WALLET_NOT_FOUND = new DepositFundsStatus("Wallet not found");
    public static final DepositFundsStatus WALLETS_LIMIT_EXCEEDED = new DepositFundsStatus("Wallets limit exceeded");


    private String status;
    private String accountNumber;

    private DepositFundsStatus(String status, String accountNumber) {
        this.status = status;
        this.accountNumber = accountNumber;
    }
    public DepositFundsStatus(String status) {
        this.status = status;
    }

    public static DepositFundsStatus success(String accountId) {
       return new DepositFundsStatus("Success", accountId);
    }

    public String getStatus() {
        return status;
    }
}
