package pl.coztymit.exchange.promotionsaga;

import jakarta.persistence.*;
import pl.coztymit.exchange.kernel.IdentityId;

@Entity
@Table(name ="new_client_promotions")
public class NewClientPromotion {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "identity_id"))
    @EmbeddedId
    private IdentityId identityId;

    private Boolean accountActivated;

    private Boolean negotiationCreated;

    public NewClientPromotion() {
    }

    public NewClientPromotion(IdentityId identityId) {
        this.identityId = identityId;
        this.accountActivated = Boolean.FALSE;
        this.negotiationCreated = Boolean.FALSE;
    }

    public boolean isComplete() {
        return accountActivated && negotiationCreated;
    }

    public void accountActivated() {
        this.accountActivated = Boolean.TRUE;
    }

    public IdentityId identityId() {
        return identityId;
    }

    public void negotiationCreated() {
        this.negotiationCreated = Boolean.TRUE;
    }
}
