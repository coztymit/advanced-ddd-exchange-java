package pl.coztymit.exchange.promotionsaga;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.Optional;

@Repository
public class NewClientPromotionRepository {

        @PersistenceContext
        private EntityManager entityManager;

        void save(NewClientPromotion saga){
                entityManager.persist(saga);
        }

        Optional<NewClientPromotion> findNewClientPromotion(IdentityId identityId) {
                return Optional.ofNullable(entityManager.find(NewClientPromotion.class, identityId));
        }

}
