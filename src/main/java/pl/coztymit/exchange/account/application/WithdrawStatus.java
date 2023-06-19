package pl.coztymit.exchange.account.application;

public class WithdrawStatus {
    public static final WithdrawStatus INSUFFICIENT_FUNDS =  new WithdrawStatus("Insufficient funds");
    public static final WithdrawStatus WITHDRAW_SUCCESS =  new WithdrawStatus("Withdraw success");
    public static final WithdrawStatus TRANSACTION_LIMIT_EXCEEDED = new WithdrawStatus("Transaction limit exceeded");
    public static final WithdrawStatus ACCOUNT_NOT_FOUND = new WithdrawStatus("Account not found");
    private String status;

    public WithdrawStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
