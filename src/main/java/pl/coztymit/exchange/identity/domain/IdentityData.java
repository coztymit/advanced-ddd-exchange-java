package pl.coztymit.exchange.identity.domain;

import pl.coztymit.exchange.kernel.IdentityId;

public class IdentityData {
    private String pesel;
    private String firstName;
    private String surname;

    private String email;

    private IdentityId identityId;


    public IdentityData(IdentityId identityId, String firstName, String surname, String pesel, String email) {
        this.identityId = identityId;
        this.pesel = pesel;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public IdentityId getIdentityId() {
        return identityId;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
