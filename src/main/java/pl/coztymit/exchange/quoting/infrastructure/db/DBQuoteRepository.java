package pl.coztymit.exchange.quoting.infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.quoting.domain.Quote;
import pl.coztymit.exchange.quoting.domain.QuoteId;
import pl.coztymit.exchange.quoting.domain.QuoteRepository;
import pl.coztymit.exchange.quoting.domain.QuoteStatus;
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
    public Optional<Quote> findActiveQuote(TraderNumber traderNumber, Currency currencyToSell, Currency currencyToBuy, Money moneyToExchange) {
        String queryString = "SELECT q FROM Quote q WHERE q.traderNumber = :traderNumber AND q.bestExchangeRate.currencyToSell = :currencyToSell AND q.bestExchangeRate.currencyToBuy = :currencyToBuy AND q.moneyToExchange = :moneyToExchange AND q.quoteStatus = :status";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("traderNumber", traderNumber);
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
    public Optional<Quote> findActiveQuote(QuoteId quoteId) {
        String queryString = "SELECT q FROM Quote q WHERE q.quoteId = :quoteId AND q.quoteStatus = :quoteStatus";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("quoteId", quoteId);
        query.setParameter("quoteStatus", QuoteStatus.PREPARED);
        try {
            Quote quote = query.getSingleResult();
            return Optional.ofNullable(quote);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Quote getQuote(QuoteId quoteId) throws QuoteNotFoundException {
        String queryString = "SELECT q FROM Quote q WHERE q.quoteId = :quoteId";
        TypedQuery<Quote> query = entityManager.createQuery(queryString, Quote.class);
        query.setParameter("quoteId", quoteId);

        try {
            Quote quote = query.getSingleResult();
            return quote;
        } catch (NoResultException e) {
            throw new QuoteNotFoundException(quoteId.toString());
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
}
