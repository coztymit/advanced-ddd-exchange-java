package pl.coztymit.exchange.promotionsaga;

import pl.coztymit.exchange.kernel.IdentityId;

public class NewClientPromotionCompleted {
    private IdentityId identityId;

    public NewClientPromotionCompleted(IdentityId identityId){
        this.identityId = identityId;
    }
}
