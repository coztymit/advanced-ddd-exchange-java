package pl.coztymit.exchange.quoting.infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.*;
import pl.coztymit.exchange.quoting.domain.exception.QuoteNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class DBQuoteRepository implements QuoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Quote quote) {
        entityManager.persist(quote);
    }

    @Override
    public Optional<Quote> findActiveQuote(Requester requester, Currency currencyToSell, Currency currencyToBuy, MoneyToExchange moneyToExchange) {
        String queryString = "SELECT q FROM Quote q WHERE q.requester = :requester AND q.bestExchangeRate.currencyToSell = :currencyToSell AND q.bestExchangeRate.currencyToBuy = :currencyToBuy AND q.moneyToExchange = :moneyToExchange AND q.quoteStatus = :status";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("requester", requester);
        query.setParameter("currencyToSell", currencyToSell);
        query.setParameter("currencyToBuy", currencyToBuy);
        query.setParameter("moneyToExchange", moneyToExchange);
        query.setParameter("status", QuoteStatus.PREPARED);

        try {
            Quote quote = query.getSingleResult();
            return Optional.ofNullable(quote);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Quote> findActiveQuote(QuoteNumber quoteNumber) {
        String queryString = "SELECT q FROM Quote q WHERE q.quoteId = :quoteId AND q.quoteStatus = :quoteStatus";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("quoteId", quoteNumber);
        query.setParameter("quoteStatus", QuoteStatus.PREPARED);
        try {
            Quote quote = query.getSingleResult();
            return Optional.ofNullable(quote);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Quote getQuote(QuoteNumber quoteNumber) throws QuoteNotFoundException {
        String queryString = "SELECT q FROM Quote q WHERE q.quoteId = :quoteId";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("quoteId", quoteNumber);

        try {
            Quote quote = query.getSingleResult();
            return quote;
        } catch (NoResultException e) {
            throw new QuoteNotFoundException(quoteNumber.toString());
        }
    }

    @Override
    public List<Quote> findAllQuotesToExpire() {
        String queryString = "SELECT q FROM Quote q WHERE q.expirationDate.expirationDate < :currentDate AND q.quoteStatus = :status";

        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("status", QuoteStatus.PREPARED);
        query.setParameter("currentDate", LocalDateTime.now());

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Quote> findAllQuotesToExpireByCurrency(Currency currencyToSell, Currency currencyToBuy) {
        String queryString = "SELECT q FROM Quote q WHERE q.bestExchangeRate.currencyToSell = :currencyToSell AND q.bestExchangeRate.currencyToBuy = :currencyToBuy AND q.quoteStatus = :status";

        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("status", QuoteStatus.PREPARED);
        query.setParameter("currencyToSell", currencyToSell);
        query.setParameter("currencyToBuy", currencyToBuy);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
