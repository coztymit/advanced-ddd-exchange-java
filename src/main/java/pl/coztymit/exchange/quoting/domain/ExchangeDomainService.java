package pl.coztymit.exchange.quoting.domain;


import org.springframework.stereotype.Service;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.policy.OneDayQuoteExpirationDatePolicy;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeDomainService {
    public ExchangeRate getBestExchangeRate(List<ExchangeRateAdvisor> advisors, Currency currencyToSell, Currency currencyToBuy) {
       // tu moźna dodać politykę jeżeli isMoreFavorableThan nie jest jedyną opcją

        return advisors.stream()
                .map(advisor -> advisor.exchangeRate(currencyToSell, currencyToBuy))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce((rate1, rate2) -> rate1.isMoreFavorableThan(rate2) ? rate1 : rate2)
                .orElseThrow(() -> new RuntimeException("No exchange rate available"));
    }

    public QuoteExpirationDatePolicy determineQuoteExpirationDatePolicy(TraderNumber traderNumber) {
        //To określamy na poziomie subskrypcji

        return new OneDayQuoteExpirationDatePolicy();
    }
}
