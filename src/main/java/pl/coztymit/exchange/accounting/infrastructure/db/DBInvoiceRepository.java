package pl.coztymit.exchange.accounting.infrastructure.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.accounting.domain.Invoice;
import pl.coztymit.exchange.accounting.domain.InvoiceRepository;
import pl.coztymit.exchange.accounting.domain.Number;

import java.util.Optional;

@Repository
public class DBInvoiceRepository implements InvoiceRepository {


    public void save(Invoice invoice) {
    }

    @Override
    public Invoice get(Number number) {

        return null;
    }

    @Override
    public Optional<Invoice> find(Number number) {

        return null;
    }
}
