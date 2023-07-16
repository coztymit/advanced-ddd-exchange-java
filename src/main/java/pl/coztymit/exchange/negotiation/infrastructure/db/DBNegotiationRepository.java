package pl.coztymit.exchange.negotiation.infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.*;
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
    public boolean alreadyExistsActiveNegotiationForNegotiator(Negotiator negotiator, Currency baseCurrency, Currency targetCurrency, BigDecimal proposedRate, ProposedExchangeAmount proposedExchangeAmount) {
        String queryString = "SELECT n FROM Negotiation n WHERE n.negotiator = :negotiator AND n.targetCurrency = :targetCurrency AND n.proposedExchangeAmount.currency = :targetCurrency AND n.negotiationRate.proposedRate = :proposedRate AND n.proposedExchangeAmount = :proposedExchangeAmount AND n.status = :status";
        TypedQuery<Negotiation> query = entityManager.createQuery(queryString, Negotiation.class);
        query.setParameter("negotiator", negotiator);
        query.setParameter("targetCurrency", targetCurrency);
        query.setParameter("targetCurrency", targetCurrency);
        query.setParameter("proposedRate", proposedRate);
        query.setParameter("proposedExchangeAmount", proposedExchangeAmount);
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
    public Optional<BigDecimal> findAcceptedActiveNegotiation(Negotiator negotiator, Currency baseCurrency, Currency targetCurrency, ProposedExchangeAmount proposedExchangeAmount) {
        String queryString = "SELECT n.negotiationRate.proposedRate FROM Negotiation n WHERE n.negotiator = :negotiator  AND n.baseCurrency = :baseCurrency AND n.targetCurrency = :targetCurrency AND n.proposedExchangeAmount = :proposedExchangeAmount AND n.status = :status";
        TypedQuery<BigDecimal> query = entityManager.createQuery(queryString, BigDecimal.class);
        query.setParameter("negotiator", negotiator);
        query.setParameter("baseCurrency", baseCurrency);
        query.setParameter("targetCurrency", targetCurrency);
        query.setParameter("proposedExchangeAmount", proposedExchangeAmount);
        query.setParameter("status", Status.APPROVED);

        try {
            BigDecimal approvedRate = query.getSingleResult();
            return Optional.ofNullable(approvedRate);
        } catch (NoResultException e) {
           return Optional.empty();
        }
    }
}
