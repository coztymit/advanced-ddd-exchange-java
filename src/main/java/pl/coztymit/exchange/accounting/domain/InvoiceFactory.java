package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.accounting.domain.policy.EURLineLimitPolicy;
import pl.coztymit.exchange.accounting.domain.policy.LineLimitPolicy;
import pl.coztymit.exchange.kernel.Money;

import java.util.List;

public class InvoiceFactory {

    // dla przyspieszenia
    private final Seller seller = new Seller("Coztymit", "Jan", "Kowalski", new NIP());
    private final Buyer buyer = new Buyer("Coztymit", "Jan", "Kwiatkowski", new NIP());

    public Invoice createInvoice(List<LineAttributes> lineAttributes) {
        Invoice invoice = new Invoice(seller, buyer);

        return prepareInvoice(lineAttributes, invoice);
    }

    public Invoice createInvoice(String number, List<LineAttributes> lineAttributes) {
        Invoice invoice = new Invoice(new Number(number), seller, buyer);

        return prepareInvoice(lineAttributes, invoice);
    }

    Invoice createInvoice(List<LineAttributes> lineAttributes, LineLimitPolicy limitPolicy) {

        //Czy nie łamiemy tu wprowadznai agregatu w nieodpowieni stan?
        Invoice invoice = new Invoice(seller, buyer);

        return prepareInvoice(lineAttributes, invoice);
    }

    static Invoice prepareInvoice(List<LineAttributes> linesAttributes, Invoice invoice) {
        //okresl polityke ilosci pozycji
        LineLimitPolicy limitPolicy = new EURLineLimitPolicy();

        linesAttributes.forEach(line ->
                invoice.addLine(
                        new Line(new ProductNumber(line.productNumber()), new Money(line.productValue())),
                        limitPolicy));
        return invoice;
    }

}
