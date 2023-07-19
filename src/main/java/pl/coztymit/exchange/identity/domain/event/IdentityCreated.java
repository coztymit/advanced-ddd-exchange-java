package pl.coztymit.exchange.identity.domain.event;

import pl.coztymit.exchange.kernel.IdentityId;

public record IdentityCreated (
        IdentityId identityId,
        String pesel,
        String firstName, String surname, String email) {
    }

