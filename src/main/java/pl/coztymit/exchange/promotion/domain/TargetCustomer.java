package pl.coztymit.exchange.promotion.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import pl.coztymit.exchange.kernel.IdentityId;

@Embeddable
public class TargetCustomer {
    @AttributeOverride(name = "uuid",
            column = @Column(name = "identity_id"))
    private IdentityId identityId;

    private TargetCustomer() {
    }
    public TargetCustomer(IdentityId identityId) {
        this.identityId = identityId;
    }
}
