package pl.coztymit.exchange.quoting.ui.crone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.quoting.application.QuoteApplicationService;
import pl.coztymit.exchange.quoting.domain.Quote;

@Component
public class QuoteExpirationScheduler {

    private final QuoteApplicationService quoteApplicationService;

    @Autowired
    public QuoteExpirationScheduler(QuoteApplicationService quoteApplicationService) {
        this.quoteApplicationService = quoteApplicationService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkForExpiredQuotes() {
        quoteApplicationService.expireQuotes();
    }
}
