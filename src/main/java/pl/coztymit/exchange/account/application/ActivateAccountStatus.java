package pl.coztymit.exchange.account.application;

public class ActivateAccountStatus {
    private String status;

    private ActivateAccountStatus(String status) {
        this.status = status;
    }

    public static ActivateAccountStatus successStatus() {
        return new ActivateAccountStatus("SUCCESS");
    }

    public static ActivateAccountStatus failStatus() {
        return new ActivateAccountStatus("FAIL");
    }

    public String getStatus() {
        return status;
    }
}
