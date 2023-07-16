package pl.coztymit.exchange.negotiation.infrastructure.risk.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.negotiation.domain.Negotiator;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessment;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessmentNumber;
import pl.coztymit.exchange.negotiation.domain.risk.RiskAssessmentRepository;

import java.util.Optional;

@Repository
public class DBRiskAssessmentRepository implements RiskAssessmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(RiskAssessment riskAssessment) {
        entityManager.persist(riskAssessment);
    }

    @Override
    public Optional<RiskAssessment> findByNegotiatorId(Negotiator negotiator) {
        String queryString = "SELECT r FROM RiskAssessment r WHERE r.negotiator = :negotiator";
        TypedQuery<RiskAssessment> query = entityManager.createQuery(queryString, RiskAssessment.class);
        query.setParameter("negotiator", negotiator);

        try {
            RiskAssessment riskAssessment = query.getSingleResult();
            return Optional.of(riskAssessment);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<RiskAssessment> findByRiskAssessmentNumber(RiskAssessmentNumber riskAssessmentNumber) {
        String queryString = "SELECT r FROM RiskAssessment r WHERE r.riskAssessmentNumber = :riskAssessmentNumber";
        TypedQuery<RiskAssessment> query = entityManager.createQuery(queryString, RiskAssessment.class);
        query.setParameter("riskAssessmentNumber", riskAssessmentNumber);

        try {
            RiskAssessment riskAssessment = query.getSingleResult();
            return Optional.of(riskAssessment);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
