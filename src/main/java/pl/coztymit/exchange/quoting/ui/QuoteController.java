package pl.coztymit.exchange.quoting.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.account.application.TransferFundsStatus;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.account.ui.AccountController;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.application.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class QuoteController {
    private Log LOG = LogFactory.getLog(QuoteController.class);
    @Autowired
    private QuoteApplicationService quoteApplicationService;


    @PostMapping("/prepare-quote")
    public PrepareQuoteStatus prepareQuote(@RequestBody PrepareQuoteRequest prepareQuoteRequest) {
        PrepareQuoteCommand prepareQuoteCommand = new PrepareQuoteCommand(
                prepareQuoteRequest.traderNumber(),
                prepareQuoteRequest.moneyToExchange(),
                prepareQuoteRequest.currencyToSell(),
                prepareQuoteRequest.currencyToBuy());

        return quoteApplicationService.prepareQuote(prepareQuoteCommand);
    }

    @PutMapping("/{quoteId}/accept")
    public AcceptQuoteStatus acceptQuote(@PathVariable UUID quoteId) {
        return quoteApplicationService.acceptQuote(quoteId);
    }
}
