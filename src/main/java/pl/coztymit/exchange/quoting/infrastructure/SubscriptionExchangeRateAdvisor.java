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

        //obecnie trader możę wykupić 2 subskrypce Standard Złoty i Platynowy
        //które dają możliwości
        // obniżenie stawki w weekendy o 0.5%
        // obniżenie stawki w święta o 1%

        return Optional.empty();
    }
}
