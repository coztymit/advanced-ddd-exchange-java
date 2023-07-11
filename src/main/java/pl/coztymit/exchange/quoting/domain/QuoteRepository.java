package pl.coztymit.exchange.quoting.domain;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.quoting.domain.exception.QuoteNotFoundException;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository {

    void save(Quote quote);

    Optional<Quote> findActiveQuote(TraderNumber traderNumber, Currency currencyToSell, Currency currencyToBuy, Money moneyToExchange);
    Optional<Quote> findActiveQuote(QuoteId quoteId);
    Quote getQuote(QuoteId quoteId) throws QuoteNotFoundException;
    List<Quote> findAllQuotesToExpire();
}
