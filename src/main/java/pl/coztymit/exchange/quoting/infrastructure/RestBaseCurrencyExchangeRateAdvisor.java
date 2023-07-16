package pl.coztymit.exchange.quoting.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.application.CurrencyPairApplicationService;
import pl.coztymit.exchange.currency.application.CurrencyPairResponse;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.*;

import java.util.Optional;

@Component
public class RestBaseCurrencyExchangeRateAdvisor implements ExchangeRateAdvisor {


    @Autowired
    private CurrencyPairApplicationService currencyPairApplicationService;

    @Override
    public Optional<ExchangeRate> exchangeRate(Requester requester, MoneyToExchange moneyToExchange, Currency currencyToSell, Currency currencyToBuy) {
        CurrencyPairResponse currencyPair = currencyPairApplicationService.getCurrencyPair(currencyToSell, currencyToBuy);
        if (currencyPair.getStatus().equals("FAILURE")){
            return Optional.empty();
        }

        if (currencyPair.getAdjustedExchangeRate() == null){
            return Optional.of(new ExchangeRate(currencyToSell, currencyToBuy, new Rate(currencyPair.getExchangeRate())));
        }else {
            return Optional.of(new ExchangeRate(currencyToSell, currencyToBuy, new Rate(currencyPair.getAdjustedExchangeRate())));
        }
    }
}
