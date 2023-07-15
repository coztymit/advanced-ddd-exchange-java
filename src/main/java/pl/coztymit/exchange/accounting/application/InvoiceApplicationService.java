package pl.coztymit.exchange.accounting.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.accounting.domain.*;
import pl.coztymit.exchange.accounting.domain.Number;
import pl.coztymit.exchange.accounting.domain.policy.LineLimitPolicy;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceApplicationService {

    private List<InvoiceRepository> invoiceRepository;
    private InvoiceFactory factory;
    private List<NotificationSender> senders;

    @Autowired
    public InvoiceApplicationService(List<NotificationSender> senders, List<InvoiceRepository> invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.factory = new InvoiceFactory();
        this.senders = senders;
    }

    @Transactional
    public CreateStatus createInvoice (){

        Invoice invoice = this.factory.createInvoice(null);
        this.invoiceRepository.forEach(repo -> repo.save(invoice));

        senders.forEach(sender->sender.sendNotification(invoice.invoiceNumber()));
        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    @Transactional
    public CreateStatus createInvoice(String invoiceNumber)
    {

        List<LineAttributes> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine(new BigDecimal("100"), "PLN"));

        Invoice invoice = factory.createInvoice(invoiceNumber, invoiceLines);
        this.invoiceRepository.forEach( repo -> repo.save(invoice));


        /*foreach (NotificationSender sender in senders) {
           sender.SendNotification(invoice.ToString());
         }

        To oczywiscie moze zostac wzbogacone */
        return CreateStatus.Correct(invoice.invoiceNumber());

    }

    public final void approveInvoice(String number) {


        invoiceRepository.forEach(repo ->{
             Invoice invoice = repo.get(new Number(number));
             invoice.approve();
             repo.save(invoice);


             BookKeeperService bookKeeper = new BookKeeperService();
             Payment payment = bookKeeper.createPayment(invoice);
             // TODO dodać  paymentRepository.save(payment);
         });

    }

    public final CreateStatus createInvoiceByBookKeeper() {
        // Transformacja z ebiektów komunikacji

        List<LineAttributes> invoiceLines = InvoiceApplicationService.createLines();
        BookKeeperService bookKeeper = new BookKeeperService();
        LineLimitPolicy limitPolicy = bookKeeper.definePositionLimitPolicy();
        Invoice invoice = bookKeeper.createInvoice(invoiceLines, limitPolicy);
        this.invoiceRepository.forEach( repo -> repo.save(invoice));
        return CreateStatus.Correct(invoice.invoiceNumber());
    }

    private static List<LineAttributes> createLines() {
        List<LineAttributes> invoicePositions =  new ArrayList<>();
        invoicePositions.add(new InvoiceLine(new BigDecimal(100), "PLN"));
        return invoicePositions;
    }

    public final CreateStatus createCorrection(String number) {
        //TODO implementacja
        return new CreateStatus("Corrected", number);
    }

}
