package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.application.FindAcceptedActiveNegotiationRateCommand;
import pl.coztymit.exchange.negotiation.application.NegotiationApplicationService;
import pl.coztymit.exchange.negotiation.application.NegotiationRateResponse;
import pl.coztymit.exchange.quoting.domain.*;

import java.util.Optional;
import java.util.function.Function;

@Component
public class NegotiationExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Autowired
    private NegotiationApplicationService negotiationApplicationService;

    @Override
    public Optional<ExchangeRate> exchangeRate(Requester requester, MoneyToExchange moneyToExchange, Currency currencyToSell, Currency currencyToBuy) {

        FindAcceptedActiveNegotiationRateCommand findAcceptedActiveNegotiationRateCommand = new FindAcceptedActiveNegotiationRateCommand(
                requester.identityId(),
                currencyToSell,
                currencyToBuy,
                moneyToExchange.value(Function.identity()), moneyToExchange.currency(Function.identity()));

        NegotiationRateResponse negotiationRateResponse = negotiationApplicationService.findAcceptedActiveNegotiationRate(findAcceptedActiveNegotiationRateCommand);
        if (negotiationRateResponse.getStatus().equals("SUCCESS")){
            return Optional.of(new ExchangeRate(currencyToSell, currencyToBuy, new Rate(negotiationRateResponse.getRate())));
        }
            return Optional.empty();
    }
}
