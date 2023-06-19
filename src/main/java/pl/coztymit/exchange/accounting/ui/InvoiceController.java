package pl.coztymit.exchange.accounting.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coztymit.exchange.accounting.application.InvoiceApplicationService;
import pl.coztymit.exchange.accounting.infrastructure.db.DBInvoiceRepository;

import java.util.UUID;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
    private InvoiceApplicationService invoiceApplicationService;

    @Autowired
    public InvoiceController(InvoiceApplicationService invoiceApplicationService) {
        this.invoiceApplicationService = invoiceApplicationService;
    }

    // TWORZY FAKTURE O LOSOWYM NUMERZE
    // localhost:5000/invoice
    // przyklad localhost:5000/invoice/
    @GetMapping("/")
    public String createinvoice() {
        return this.invoiceApplicationService.createInvoice().toString();
    }

    // TWORZY FAKTURE O PODANYM NUMERZE - GET dla uproszczenia
    // localhost:5000/invoice/{numerfaktury}
    // przyklad  localhost:5000/invoice/78d6eb0a-449f-4dc5-a511-d046e685af61
    // UUID musi być w poprawnej formie 83

    @GetMapping("/{number}")
    public String createInvoiceByNumber(@PathVariable String number) {
        try {
            return invoiceApplicationService.createInvoice(number).toString();
        } catch (Exception e) {
            LOGGER.error("Numer błędu: " + UUID.randomUUID().toString(), e);
            return "Nie udało się utworzyć faktury o numerze: " + number + "\n" +
                    "Numer błędu: " + UUID.randomUUID().toString();
        }

    }

    public void removeInvoice(String number) {
        //invoiceApplicationService.removeInvoice(number);
    }

}
