package pl.coztymit.exchange.account.ui.amqp;

import pl.coztymit.exchange.kernel.IdentityId;

public record IdentityCreatedEvent (
        IdentityId identityId,
        String pesel,
        String firstName,
        String surname,
        String email) {
}
