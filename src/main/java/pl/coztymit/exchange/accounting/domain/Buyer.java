package pl.coztymit.exchange.accounting.domain;

public class Buyer {
    private String companyName;
    private String firstName;
    private String lastName;
    private NIP nip;

    public Buyer(String companyName, String firstName, String lastName, NIP nip) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nip = nip;
    }

    public String toString() {
        return companyName + " : " + firstName + " : " + lastName;
    }
}
