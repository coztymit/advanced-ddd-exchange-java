package pl.coztymit.exchange.currency.infrastructure;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.currency.domain.CurrencyPair;
import pl.coztymit.exchange.currency.domain.CurrencyPairData;
import pl.coztymit.exchange.currency.domain.CurrencyPairId;
import pl.coztymit.exchange.currency.domain.CurrencyPairRepository;
import pl.coztymit.exchange.kernel.Currency;

import java.util.List;

@Repository
public class DBCurrencyPairRepository implements CurrencyPairRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(CurrencyPair currencyPair) {
        entityManager.persist(currencyPair);
    }

    @Override
    public CurrencyPair findById(CurrencyPairId currencyPairId) {
        String queryString = "SELECT cp FROM CurrencyPair cp WHERE cp.currencyPairId = :currencyPairId";
        TypedQuery<CurrencyPair> query = entityManager.createQuery(queryString, CurrencyPair.class);
        query.setParameter("currencyPairId", currencyPairId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Cannot find CurrencyPair for the provided ID");
        }
    }

    @Override
    public boolean alreadyExists(Currency baseCurrency, Currency targetCurrency) {
        String queryString = "SELECT count(cp) FROM CurrencyPair cp WHERE cp.baseCurrency = :baseCurrency AND cp.targetCurrency = :targetCurrency";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);
        long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public CurrencyPair findByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency) {
        String queryString = "SELECT cp FROM CurrencyPair cp WHERE cp.baseCurrency = :baseCurrency AND cp.targetCurrency = :targetCurrency";
        TypedQuery<CurrencyPair> query = entityManager.createQuery(queryString, CurrencyPair.class);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Cannot find CurrencyPair for the provided baseCurrency and targetCurrency");
        }
    }

    @Override
    public CurrencyPairData findDataByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency){
        String queryString = "SELECT new pl.coztymit.exchange.currency.domain.CurrencyPairData(cp.currencyPairId.uuid, cp.baseCurrency.value, cp.targetCurrency.value, cp.exchangeRate.baseRate, cp.exchangeRate.adjustedRate) FROM CurrencyPair cp WHERE cp.baseCurrency = :baseCurrency AND cp.targetCurrency = :targetCurrency";
        TypedQuery<CurrencyPairData> query = entityManager.createQuery(queryString, CurrencyPairData.class);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Cannot find CurrencyPair for the provided baseCurrency and targetCurrency");
        }
    }

    @Override
    public List<CurrencyPairData> findAll() {
        String queryString = "SELECT new pl.coztymit.exchange.currency.domain.CurrencyPairData(cp.currencyPairId.uuid, cp.baseCurrency.value, cp.targetCurrency.value, cp.exchangeRate.baseRate, cp.exchangeRate.adjustedRate) FROM CurrencyPair cp";
        TypedQuery<CurrencyPairData> query = entityManager.createQuery(queryString, CurrencyPairData.class);
        return query.getResultList();
    }
}
