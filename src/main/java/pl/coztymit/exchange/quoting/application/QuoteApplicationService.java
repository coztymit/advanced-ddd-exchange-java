package pl.coztymit.exchange.quoting.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.account.application.AccountApplicationService;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;


import pl.coztymit.exchange.quoting.domain.*;
import pl.coztymit.exchange.quoting.domain.exception.QuoteNotFoundException;
import pl.coztymit.exchange.quoting.domain.policy.OneDayQuoteExpirationDatePolicy;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteApplicationService {
    private Log LOG = LogFactory.getLog(QuoteApplicationService.class);
    //Zadanie 3 - tu mogą być advisory z nazwami typu subskrypcja itd. obecna forma jest popranwa do zadania zmieinć to pojedyncze advisory
    @Autowired
    private List<ExchangeRateAdvisor> currencyExchangeRateAdvisors;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private ExchangeDomainService exchangeDomainService;

    @Transactional
    public PrepareQuoteStatus prepareQuote(PrepareQuoteCommand prepareQuoteCommand){

        Currency currencyToSell = new Currency(prepareQuoteCommand.currencyToSell());
        Currency currencyToBuy = new Currency(prepareQuoteCommand.currencyToBuy());
        TraderNumber traderNumber = prepareQuoteCommand.traderNumber();
        Money moneyToExchange = prepareQuoteCommand.moneyToExchange();

        Optional<Quote> quote = quoteRepository.findActiveQuote(traderNumber, currencyToSell, currencyToBuy, moneyToExchange);
        if (quote.isPresent()){
            //TODO - zmieić na odpowiedź z danymi albo event bus :)
            return PrepareQuoteStatus.QUOTE_EXISTS;
        }

        ExchangeRate bestExchangeRate = exchangeDomainService.getBestExchangeRate(currencyExchangeRateAdvisors, currencyToSell, currencyToBuy);
        Money moneyExchanged = bestExchangeRate.exchange(moneyToExchange);

        QuoteExpirationDatePolicy quoteExpirationDatePolicy = exchangeDomainService.determineQuoteExpirationDatePolicy(traderNumber);

        Quote preparedQuote =
                new Quote(
                        prepareQuoteCommand.traderNumber(),
                        bestExchangeRate,
                        moneyToExchange,
                        moneyExchanged,
                        quoteExpirationDatePolicy);

        quoteRepository.save(preparedQuote);

        return PrepareQuoteStatus.prepareSuccessStatus(moneyExchanged);

    }

    @Transactional
    public AcceptQuoteStatus acceptQuote(UUID quoteId) {

        try {
            Quote quote = quoteRepository.getQuote(new QuoteId(quoteId));
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
    public void expireAllQuotesForCurrency(Currency currency) {
        //TODO zadanie zastanowić się jak to ogarnąć - szyna rest - skąd ma vbyć request i jaki kierunek zależności
        List<Quote> quotes = quoteRepository.findAllQuotesToExpire();
        quotes.forEach(quote -> {
            quote.expire();
            quoteRepository.save(quote);
        });
    }
}
