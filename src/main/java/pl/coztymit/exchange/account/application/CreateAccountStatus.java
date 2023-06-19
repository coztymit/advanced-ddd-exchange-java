package pl.coztymit.exchange.account.application;

import pl.coztymit.exchange.kernel.IdentityId;

public class CreateAccountStatus {
    private IdentityId identityId;
    private String accountId;
    private String traderNumber;
    private String status;

    private CreateAccountStatus(String status, IdentityId identityId, String accountId, String traderNumber) {
        this.status = status;
        this.identityId = identityId;
        this.accountId = accountId;
        this.traderNumber = traderNumber;
    }
    private CreateAccountStatus(String status, IdentityId identityId) {
        this.status = status;
        this.identityId = identityId;
    }
    public static CreateAccountStatus success(String status, IdentityId identityId, String accountId, String traderNumber) {
        return new CreateAccountStatus(status, identityId, accountId, traderNumber);
    }

    public static CreateAccountStatus createFailAccountStatus(String status, IdentityId identityId) {
        return new CreateAccountStatus(status, identityId);
    }

    public IdentityId getIdentityId() {
        return identityId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTraderNumber() {
        return traderNumber;
    }

    public String getStatus() {
        return status;
    }

}
