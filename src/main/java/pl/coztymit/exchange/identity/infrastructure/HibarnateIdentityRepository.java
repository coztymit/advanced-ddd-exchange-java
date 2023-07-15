package pl.coztymit.exchange.identity.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.identity.domain.Identity;
import pl.coztymit.exchange.identity.domain.IdentityData;
import pl.coztymit.exchange.identity.domain.IdentityRepository;
import pl.coztymit.exchange.identity.domain.PESEL;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

@Repository
public class HibarnateIdentityRepository implements IdentityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Identity identity) {
        entityManager.persist(identity);
    }

    @Override
    public List<IdentityId> findIdentityIds() {
        TypedQuery<IdentityId> query = entityManager.createQuery("SELECT i.identityId FROM Identity i", IdentityId.class);
        return query.getResultList();
    }

    @Override
    public Optional<IdentityData> findByIdentityId(IdentityId identityId) {
        TypedQuery<IdentityData> query = entityManager.createQuery("SELECT new pl.coztymit.exchange.identity.domain.IdentityData(i.identityId, i.firstName.value, i.surname.value, i.pesel.value, i.email.value) FROM Identity i WHERE i.identityId = :identityId", IdentityData.class);
        query.setParameter("identityId", identityId);
        return query.getResultStream().findFirst();
    }

    @Override
    public boolean existsByPesel(PESEL pesel) {
        TypedQuery<IdentityId> query = entityManager.createQuery("SELECT i.identityId FROM Identity i WHERE i.pesel = :pesel", IdentityId.class);
        query.setParameter("pesel", pesel);
        return !query.getResultList().isEmpty();
    }
}
