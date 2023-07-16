package pl.coztymit.exchange.quoting.domain;


import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.policy.OneDayQuoteExpirationDatePolicy;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

import java.util.List;

@Service
public class ExchangeDomainService {
    public BestExchangeRate getBestExchangeRate(Requester requester, MoneyToExchange moneyToExchange, List<ExchangeRateAdvisor> advisors, Currency currencyToSell, Currency currencyToBuy) {
       return null;
    }

    public QuoteExpirationDatePolicy determineQuoteExpirationDatePolicy(Requester requester) {
        return new OneDayQuoteExpirationDatePolicy();
    }
}
