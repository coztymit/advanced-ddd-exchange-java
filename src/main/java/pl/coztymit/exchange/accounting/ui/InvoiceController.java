package pl.coztymit.exchange.accounting.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coztymit.exchange.accounting.application.InvoiceApplicationService;

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

    @GetMapping("/")
    public String createinvoice() {
        return this.invoiceApplicationService.createInvoice().toString();
    }


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
