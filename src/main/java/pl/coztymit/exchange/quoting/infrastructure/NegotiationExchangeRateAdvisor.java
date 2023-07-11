package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;
import pl.coztymit.exchange.quoting.domain.ExchangeRateAdvisor;

import java.util.Optional;
@Component
public class NegotiationExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Override
    public Optional<ExchangeRate> exchangeRate(Currency currencyToSell, Currency currencyToBuy) {
        return Optional.empty();
    }
}
