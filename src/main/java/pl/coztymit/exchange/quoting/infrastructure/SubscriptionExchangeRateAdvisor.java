package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;
import pl.coztymit.exchange.quoting.domain.ExchangeRateAdvisor;
import pl.coztymit.exchange.quoting.domain.MoneyToExchange;
import pl.coztymit.exchange.quoting.domain.Requester;

import java.util.Optional;
@Component
public class SubscriptionExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Override
    public Optional<ExchangeRate> exchangeRate(Requester requester, MoneyToExchange moneyToExchange, Currency currencyToSell, Currency currencyToBuy) {
        return Optional.empty();
    }
}
