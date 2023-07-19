package pl.coztymit.exchange.negotiation.infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.Status;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.SupportedCurrency;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.SupportedCurrencyRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DBSupportedCurrencyRepository implements SupportedCurrencyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(SupportedCurrency supportedCurrency) {
        entityManager.persist(supportedCurrency);
    }

    @Override
    public Optional<SupportedCurrency> findByCurrency(Currency baseCurrency, Currency targetCurrency) {
        String queryString = "SELECT sc FROM SupportedCurrency sc WHERE sc.baseCurrency = :baseCurrency AND sc.targetCurrency = :targetCurrency";
        TypedQuery<SupportedCurrency> query = entityManager.createQuery(queryString, SupportedCurrency.class);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);

        List<SupportedCurrency> supportedCurrencies = query.getResultList();
        return supportedCurrencies.isEmpty() ? Optional.empty() : Optional.of(supportedCurrencies.get(0));
    }

    @Override
    public Optional<SupportedCurrency> findActiveByCurrency(Currency baseCurrency, Currency targetCurrency){
        String queryString = "SELECT sc FROM SupportedCurrency sc WHERE sc.baseCurrency = :baseCurrency AND sc.targetCurrency = :targetCurrency AND sc.status = :status";
        TypedQuery<SupportedCurrency> query = entityManager.createQuery(queryString, SupportedCurrency.class);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);
        query.setParameter("status", Status.ACTIVE);

        List<SupportedCurrency> supportedCurrencies = query.getResultList();
        return supportedCurrencies.isEmpty() ? Optional.empty() : Optional.of(supportedCurrencies.get(0));
    }
}
