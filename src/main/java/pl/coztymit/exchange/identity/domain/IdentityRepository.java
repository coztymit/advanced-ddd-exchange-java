package pl.coztymit.exchange.identity.domain;

import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

public interface IdentityRepository {
    void save(Identity identity);

    List<IdentityId> findIdentityIds();

    Optional<IdentityData> findByIdentityId(IdentityId identityId);

    boolean existsByPesel(PESEL pesel);
}
