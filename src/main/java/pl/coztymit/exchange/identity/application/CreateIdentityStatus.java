package pl.coztymit.exchange.identity.application;

import pl.coztymit.exchange.kernel.IdentityId;

public class CreateIdentityStatus {
    private static final String IDENTITY_EXISTS = new String("IDENTITY_EXISTS");
    private static final String IDENTITY_CREATED = new String("IDENTITY_CREATED");

    private final String status;
    private IdentityId identityId;

    private CreateIdentityStatus(String status) {
        this.status = status;
    }

    private CreateIdentityStatus(String status, IdentityId identityId) {
        this.status = status;
        this.identityId = identityId;
    }
    public static CreateIdentityStatus prepareSuccessStatus(IdentityId identityId){
        return new CreateIdentityStatus(IDENTITY_CREATED, identityId);
    }

    public static CreateIdentityStatus prepareExistsStatus(){
        return new CreateIdentityStatus(IDENTITY_EXISTS);
    }

    public String getStatus() {
        return status;
    }


    public IdentityId getIdentityId(){
        return this.identityId;
    }
}
