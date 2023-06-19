package pl.coztymit.exchange.identity.application;

import pl.coztymit.exchange.identity.domain.Email;
import pl.coztymit.exchange.kernel.IdentityId;

public class IdentityResponse {
    private String pesel;
    private String firstName;
    private String surname;

    private IdentityId identityId;

    private String email;

    public IdentityResponse(IdentityId identityId, String firstName, String surname, String pesel, String email) {
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
