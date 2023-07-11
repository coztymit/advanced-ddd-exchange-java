package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.DiscountLevel;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;
import pl.coztymit.exchange.quoting.domain.ExchangeRateAdvisor;

import javax.swing.text.html.Option;
import java.util.Optional;
@Component
public class SubscriptionExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Override
    public Optional<ExchangeRate> exchangeRate(Currency currencyToSell, Currency currencyToBuy) {

        //obecnie trader możę wykupić 2 subskrypce Standard Złoty i Platynowy
        //które dają możliwości
        // obniżenie stawki w weekendy o 0.5%
        // obniżenie stawki w święta o 1%
        //

        /*if (subscription.subscriptionEquals("GOLD")) {
            return baseExchangeRate.applyDiscount(DiscountLevel.FIVE_PERCENT);
        } else {
            return null;
        }*/
        return Optional.empty();
    }
}
