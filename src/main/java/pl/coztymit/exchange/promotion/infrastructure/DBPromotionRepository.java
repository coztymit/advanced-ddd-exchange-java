package pl.coztymit.exchange.promotion.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.promotion.domain.Promotion;
import pl.coztymit.exchange.promotion.domain.PromotionRepository;

@Repository
public class DBPromotionRepository implements PromotionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Promotion promotion) {
        entityManager.persist(promotion);
    }
}
