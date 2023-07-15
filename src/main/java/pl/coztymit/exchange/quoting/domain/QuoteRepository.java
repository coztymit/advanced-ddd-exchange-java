package pl.coztymit.exchange.quoting.domain;

import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.exception.QuoteNotFoundException;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository {

    void save(Quote quote);

    Optional<Quote> findActiveQuote(Requester requester, Currency currencyToSell, Currency currencyToBuy, MoneyToExchange moneyToExchange);
    Optional<Quote> findActiveQuote(QuoteNumber quoteNumber);
    Quote getQuote(QuoteNumber quoteNumber) throws QuoteNotFoundException;
    List<Quote> findAllQuotesToExpire();
    List<Quote> findAllQuotesToExpireByCurrency(Currency currencyToSell, Currency currencyToBuy);
}
