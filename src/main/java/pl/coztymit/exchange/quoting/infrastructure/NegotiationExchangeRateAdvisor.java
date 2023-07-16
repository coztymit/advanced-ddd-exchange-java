package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.application.NegotiationApplicationService;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;
import pl.coztymit.exchange.quoting.domain.ExchangeRateAdvisor;
import pl.coztymit.exchange.quoting.domain.MoneyToExchange;
import pl.coztymit.exchange.quoting.domain.Requester;

import java.util.Optional;
@Component
public class NegotiationExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Autowired
    private NegotiationApplicationService negotiationApplicationService;

    @Override
    public Optional<ExchangeRate> exchangeRate(Requester requester, MoneyToExchange moneyToExchange, Currency currencyToSell, Currency currencyToBuy) {

        /**FindAcceptedActiveNegotiationRateCommand findAcceptedActiveNegotiationRateCommand = new FindAcceptedActiveNegotiationRateCommand(
                requester.identityId(),
                currencyToSell,
                currencyToBuy,
                moneyToExchange.toMoney());

        NegotiationRateResponse acceptedActiveNegotiationRate = negotiationApplicationService.findAcceptedActiveNegotiationRate(findAcceptedActiveNegotiationRateCommand);
*/
        return Optional.empty();
    }
}
