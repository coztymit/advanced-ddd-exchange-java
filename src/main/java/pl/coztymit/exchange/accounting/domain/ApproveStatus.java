package pl.coztymit.exchange.accounting.domain;

public class ApproveStatus {
    public static ApproveStatus APPROVED = new ApproveStatus("APPROVED");
    public static ApproveStatus DRAFT = new ApproveStatus("DRAFT");
    private String value;

    private ApproveStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
