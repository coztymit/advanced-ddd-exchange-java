package pl.coztymit.exchange.kernel;

import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
@Embeddable
public class IdentityId {
    private UUID uuid;

    private IdentityId() {
    }

    public IdentityId(UUID uuid) {
        requireNonNull(uuid, "IdentityId cannot be null");
        this.uuid = uuid;
    }

    public static IdentityId generateNewId() {
        return new IdentityId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IdentityId)) return false;
        return uuid.equals(((IdentityId) obj).uuid);

    }

    public boolean equals(IdentityId identityId) {
        if (this == identityId) return true;
        return uuid.equals(identityId.uuid);
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String toString() {
        return uuid.toString();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
