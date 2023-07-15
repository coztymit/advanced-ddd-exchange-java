package pl.coztymit.exchange.identity.application;

public record CreateIdentityCommand (String pesel, String firstName, String surname, String email) {
}
