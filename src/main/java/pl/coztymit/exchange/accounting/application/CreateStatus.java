package pl.coztymit.exchange.accounting.application;

public class CreateStatus {
    private String status;
    private String invoiceNumber;

    public static CreateStatus Correct(String number)
    {
        return new CreateStatus("SUCCES", number);
    }

    public CreateStatus(String status, String invoiceNumber) {
        this.status = status;
        this.invoiceNumber = invoiceNumber;
    }

    private void createStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status + " numer faktury: "+ this.invoiceNumber;
    }

}
