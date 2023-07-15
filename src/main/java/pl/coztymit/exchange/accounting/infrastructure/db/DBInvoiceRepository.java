package pl.coztymit.exchange.accounting.infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.accounting.domain.Invoice;
import pl.coztymit.exchange.accounting.domain.InvoiceRepository;
import pl.coztymit.exchange.accounting.domain.Number;

import java.util.Optional;

@Repository
public class DBInvoiceRepository implements InvoiceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Invoice invoice) {
        entityManager.persist(invoice);
    }

    @Override
    public Invoice get(Number number) {
        return find(number).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public Optional<Invoice> find(Number number) {
        String queryString = "SELECT i FROM Invoice i WHERE i.number = :number";
        TypedQuery<Invoice> query = entityManager.createQuery(queryString, Invoice.class);
        query.setParameter("number", number);
        try {
            Invoice invoice = query.getSingleResult();
            return Optional.ofNullable(invoice);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
