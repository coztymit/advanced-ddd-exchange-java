package pl.coztymit.exchange.negotiation.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.application.CurrencyPairResponse;
import pl.coztymit.exchange.currency.application.CurrencyPairApplicationService;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.application.BaseExchangeRateAdvisor;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class APPBaseExchangeRateAdvisor implements BaseExchangeRateAdvisor {
    @Autowired
    private CurrencyPairApplicationService currencyPairApplicationService;

    @Override
    public Optional<BigDecimal> baseExchangeRate(Currency baseCurrency, Currency targetCurrency) {
        CurrencyPairResponse currencyPair = currencyPairApplicationService.getCurrencyPair(baseCurrency, targetCurrency);
        if (currencyPair.getStatus().equals("FAILURE")){
            return Optional.empty();
        }
        return Optional.of(currencyPair.getExchangeRate());
    }
}
