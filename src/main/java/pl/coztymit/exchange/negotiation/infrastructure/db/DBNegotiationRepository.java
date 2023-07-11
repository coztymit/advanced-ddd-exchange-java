package pl.coztymit.exchange.negotiation.infrastructure.db;

import jakarta.persistence.*;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.Negotiation;
import pl.coztymit.exchange.negotiation.domain.NegotiationId;
import pl.coztymit.exchange.negotiation.domain.NegotiationRepository;
import pl.coztymit.exchange.negotiation.domain.Status;
import pl.coztymit.exchange.negotiation.domain.exception.NegotiationNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class DBNegotiationRepository implements NegotiationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Negotiation negotiation) {
        entityManager.persist(negotiation);
    }

    @Override
    public Negotiation findById(NegotiationId id) throws NegotiationNotFoundException {
        String queryString = "SELECT n FROM Negotiation n WHERE n.negotiationId = :negotiationId";
        TypedQuery<Negotiation> query = entityManager.createQuery(queryString, Negotiation.class);
        query.setParameter("negotiationId", id);

        try {
            Negotiation negotiation = query.getSingleResult();
            return negotiation;
        } catch (NoResultException e) {
            throw new NegotiationNotFoundException(id.toString());
        }
    }

    @Override
    public boolean alreadyExistsActiveNegotiationForTrader(TraderNumber traderNumber, Currency currency, Currency currency1, BigDecimal bigDecimal, Money money) {
        String queryString = "SELECT n FROM Negotiation n WHERE n.traderNumber = :traderNumber AND n.targetCurrency = :currency AND n.proposedExchangeAmount.currency = :currency1 AND n.negotiationRate.proposedRate = :bigDecimal AND n.proposedExchangeAmount = :money AND n.status = :status";
        TypedQuery<Negotiation> query = entityManager.createQuery(queryString, Negotiation.class);
        query.setParameter("traderNumber", traderNumber);
        query.setParameter("currency", currency);
        query.setParameter("currency1", currency1);
        query.setParameter("bigDecimal", bigDecimal);
        query.setParameter("money", money);
        query.setParameter("status", Status.PENDING);

        List<Negotiation> negotiations = query.getResultList();
        return !negotiations.isEmpty();

    }

    @Override
    public BigDecimal findApprovedRateById(NegotiationId negotiationId) throws NegotiationNotFoundException {
        String queryString = "SELECT n.negotiationRate.proposedRate FROM Negotiation n WHERE n.negotiationId = :negotiationId AND n.status = :status";
        TypedQuery<BigDecimal> query = entityManager.createQuery(queryString, BigDecimal.class);
        query.setParameter("negotiationId", negotiationId);
        query.setParameter("status", Status.APPROVED);

        try {
            BigDecimal approvedRate = query.getSingleResult();
            return approvedRate;
        } catch (NoResultException e) {
            throw new NegotiationNotFoundException(negotiationId.toString());
        }
    }

    @Override
    public BigDecimal findAcceptedActiveNegotiation(TraderNumber traderNumber, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount) throws NegotiationNotFoundException {
        String queryString = "SELECT n.negotiationRate.proposedRate FROM Negotiation n WHERE n.traderNumber = :traderNumber AND n.baseCurrency = :baseCurrency AND n.targetCurrency = :targetCurrency AND n.proposedExchangeAmount = :proposedExchangeAmount AND n.status = :status";
        TypedQuery<BigDecimal> query = entityManager.createQuery(queryString, BigDecimal.class);
        query.setParameter("traderNumber", traderNumber);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);
        query.setParameter("proposedExchangeAmount", proposedExchangeAmount);
        query.setParameter("status", Status.APPROVED);

        try {
            BigDecimal approvedRate = query.getSingleResult();
            return approvedRate;
        } catch (NoResultException e) {
            throw new NegotiationNotFoundException(traderNumber.toString());
        }
    }
}
