package pl.coztymit.exchange.quoting.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.quoting.application.AcceptQuoteStatus;
import pl.coztymit.exchange.quoting.application.PrepareQuoteCommand;
import pl.coztymit.exchange.quoting.application.PrepareQuoteStatus;
import pl.coztymit.exchange.quoting.application.QuoteApplicationService;

import java.util.UUID;

@RestController
public class QuoteController {
    private Log LOG = LogFactory.getLog(QuoteController.class);

    @Autowired
    private QuoteApplicationService quoteApplicationService;

    @PostMapping("/prepare-quote")
    public PrepareQuoteStatus prepareQuote(@RequestBody PrepareQuoteRequest prepareQuoteRequest) {
        PrepareQuoteCommand prepareQuoteCommand = new PrepareQuoteCommand(
                prepareQuoteRequest.identityId(),
                prepareQuoteRequest.moneyToExchangeValue(),
                prepareQuoteRequest.moneyToExchangeCurrency(),
                prepareQuoteRequest.currencyToSell(),
                prepareQuoteRequest.currencyToBuy());

        return quoteApplicationService.prepareQuote(prepareQuoteCommand);
    }

    @PutMapping("/{quoteId}/accept")
    public AcceptQuoteStatus acceptQuote(@PathVariable UUID quoteId) {
        return quoteApplicationService.acceptQuote(quoteId);
    }

    @PutMapping("/{quoteId}/reject")
    public AcceptQuoteStatus rejectQuote(@PathVariable UUID quoteId) {
        return quoteApplicationService.reject(quoteId);
    }
}
