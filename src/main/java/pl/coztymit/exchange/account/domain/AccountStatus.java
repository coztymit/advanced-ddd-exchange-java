package pl.coztymit.exchange.account.domain;

public class AccountStatus {
    public final static String SUCCESS = "SUCCESS";
    public final static String ACCOUNT_ALREADY_EXISTS = "ACCOUNT_ALREADY_EXISTS";

    private Account account;
    private String accountId;
    private String traderNumber;
    private String createStatus;

    private AccountStatus(String status, Account account, String accountId, String traderNumber) {
        this.createStatus = status;
        this.account = account;
        this.accountId = accountId;
        this.traderNumber = traderNumber;
    }
    private AccountStatus(String status) {
        this.createStatus = status;
    }

    public static AccountStatus createSuccessAccountStatus(String status, Account account, String accountId, String traderNumber ) {
        return new AccountStatus(status, account,  accountId, traderNumber);
    }
    public static AccountStatus createAccountAlreadyExistsStatus() {
        return new AccountStatus(ACCOUNT_ALREADY_EXISTS);
    }

    public boolean isSuccess() {
        return this.createStatus.equals(SUCCESS);
    }

    public Account account() {
        return account;
    }

    public String accountId() {
        return accountId;
    }

    public String traderNumber() {
        return traderNumber;
    }

    public String status() {
        return createStatus;
    }
}
