package pl.coztymit.exchange.identity.domain;

import pl.coztymit.exchange.kernel.IdentityId;

public class IdentityFactory {
    public Identity create(String pesel, String firstName, String surname, String email) {
        return new Identity(
                IdentityId.generateNewId(),
                new PESEL(pesel),
                new FirstName(firstName),
                new Email(email),
                new Surname(surname));
    }
}
