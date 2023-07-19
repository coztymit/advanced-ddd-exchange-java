package pl.coztymit.exchange.accounting.domain;

import java.util.Optional;


public interface InvoiceRepository {

    void save(Invoice invoice);

    Invoice get(Number number);

    Optional<Invoice> find(Number number);
}
