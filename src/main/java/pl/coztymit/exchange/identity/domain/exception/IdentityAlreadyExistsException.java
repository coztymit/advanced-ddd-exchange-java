package pl.coztymit.exchange.identity.domain.exception;

import pl.coztymit.exchange.identity.domain.PESEL;

public class IdentityAlreadyExistsException extends Exception {

    public IdentityAlreadyExistsException(PESEL pesel) {
        super("Identity with pesel: " + pesel + " already exists");
    }
}
