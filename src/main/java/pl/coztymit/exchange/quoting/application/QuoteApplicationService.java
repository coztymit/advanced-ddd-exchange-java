package pl.coztymit.exchange.quoting.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.*;
import pl.coztymit.exchange.quoting.domain.exception.QuoteNotFoundException;
import pl.coztymit.exchange.quoting.domain.policy.OneHourQuoteExpirationDatePolicy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteApplicationService {
    private Log LOG = LogFactory.getLog(QuoteApplicationService.class);

    @Autowired
    private ExchangeRateAdvisor currencyExchangeRateAdvisor;
    @Autowired
    private QuoteRepository quoteRepository;

    @Transactional
    public PrepareQuoteStatus prepareQuote(PrepareQuoteCommand prepareQuoteCommand){
        Currency currencyToSell = new Currency(prepareQuoteCommand.currencyToSell());
        Currency currencyToBuy = new Currency(prepareQuoteCommand.currencyToBuy());
        Requester requester = new Requester(prepareQuoteCommand.identityId());

        MoneyToExchange moneyToExchange = new MoneyToExchange(prepareQuoteCommand.moneyToExchangeValue(),
                new Currency(prepareQuoteCommand.moneyToExchangeCurrency()));

        Optional<Quote> quote = quoteRepository.findActiveQuote(requester, currencyToSell, currencyToBuy, moneyToExchange);
        if (quote.isPresent()){
            return PrepareQuoteStatus.prepareExistsStatus(quote.get().getQuoteId());
        }

        BestExchangeRate bestExchangeRate = currencyExchangeRateAdvisor.exchangeRate(requester, moneyToExchange, currencyToSell, currencyToBuy)
                .map(rate -> new BestExchangeRate(rate.getCurrencyToSell(), rate.getCurrencyToBuy(), rate.getRate()) )
                .orElseThrow(() -> new RuntimeException("No exchange rate found"));

        MoneyExchanged moneyExchanged = bestExchangeRate.exchange(moneyToExchange);

        Quote preparedQuote =
                new Quote(
                        requester,
                        bestExchangeRate,
                        moneyToExchange,
                        moneyExchanged,
                        new OneHourQuoteExpirationDatePolicy());

        quoteRepository.save(preparedQuote);

        return PrepareQuoteStatus.prepareSuccessStatus(moneyExchanged, preparedQuote.getQuoteId());
    }

    @Transactional
    public AcceptQuoteStatus acceptQuote(UUID quoteId) {

        try {
            Quote quote = quoteRepository.getQuote(new QuoteNumber(quoteId));
            quote.accept();
            quoteRepository.save(quote);
            return AcceptQuoteStatus.SUCCESS;
        } catch (QuoteNotFoundException e) {
            LOG.error("Quote not found", e);
            return AcceptQuoteStatus.QUOTE_NOT_FOUND;
        }
    }

    @Transactional
    public void expireQuotes() {

        List<Quote> quotes = quoteRepository.findAllQuotesToExpire();
        quotes.forEach(quote -> {
            quote.expire();
            quoteRepository.save(quote);
        });
    }

    @Transactional
    public void expireAllQuotesForCurrency(Currency currencyToSell, Currency currencyToBuy) {
        List<Quote> quotes = quoteRepository.findAllQuotesToExpireByCurrency(currencyToSell, currencyToBuy);
        quotes.forEach(quote -> {
            quote.expire();
            quoteRepository.save(quote);
        });
    }

    @Transactional
    public AcceptQuoteStatus reject(UUID quoteId) {
        try {
            Quote quote = quoteRepository.getQuote(new QuoteNumber(quoteId));
            quote.reject();
            quoteRepository.save(quote);
            return AcceptQuoteStatus.SUCCESS;
        } catch (QuoteNotFoundException e) {
            LOG.error("Quote not found", e);
            return AcceptQuoteStatus.QUOTE_NOT_FOUND;
        }
    }
}
