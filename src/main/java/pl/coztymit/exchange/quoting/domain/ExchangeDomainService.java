package pl.coztymit.exchange.quoting.domain;


import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.policy.OneDayQuoteExpirationDatePolicy;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeDomainService {
    public BestExchangeRate getBestExchangeRate(Requester requester, MoneyToExchange moneyToExchange, List<ExchangeRateAdvisor> advisors, Currency currencyToSell, Currency currencyToBuy) {
        ExchangeRate exchangeRate = advisors.stream()
                .map(advisor -> advisor.exchangeRate(requester, moneyToExchange, currencyToSell, currencyToBuy))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce((rate1, rate2) -> rate1.isMoreFavorableThan(rate2) ? rate1 : rate2)
                .orElseThrow(() -> new RuntimeException("No exchange rate available"));

        return new BestExchangeRate(exchangeRate.getCurrencyToSell(), exchangeRate.getCurrencyToBuy(), exchangeRate.getRate());
    }

    public QuoteExpirationDatePolicy determineQuoteExpirationDatePolicy(Requester requester) {
        return new OneDayQuoteExpirationDatePolicy();
    }
}
