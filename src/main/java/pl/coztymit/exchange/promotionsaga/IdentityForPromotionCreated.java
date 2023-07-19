package pl.coztymit.exchange.promotionsaga;

import pl.coztymit.exchange.kernel.IdentityId;

public record IdentityForPromotionCreated  (
        IdentityId identityId,
        String pesel,
        String firstName,
        String surname,
        String email) {
}

