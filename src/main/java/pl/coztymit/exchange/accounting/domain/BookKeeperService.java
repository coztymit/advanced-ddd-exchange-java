package pl.coztymit.exchange.accounting.domain;

import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.accounting.domain.policy.PLNLineLimitPolicy;
import pl.coztymit.exchange.accounting.domain.policy.LineLimitPolicy;

import java.util.List;

//Service Domain
public class BookKeeperService {
    InvoiceFactory factory = new InvoiceFactory();


    public Invoice createInvoice(List<LineAttributes> lineAttributes, LineLimitPolicy limitPolicy) {
        //Okresla polityki
        return factory.createInvoice(lineAttributes, limitPolicy);
    }

    public Invoice createInvoiceCorrection(List<LineAttributes> lineAttributes) {
        return factory.createInvoice(lineAttributes);
    }

    public Payment createPayment(Invoice invoice) {
        // na podstawie warunków biznesowych tworzy nowey obiekt platnosci
        return new Payment();
    }

    public LineLimitPolicy definePositionLimitPolicy() {
        //tu powinna byc implementacja przypadkow okreslania polityki
        return new PLNLineLimitPolicy();
    }
}
