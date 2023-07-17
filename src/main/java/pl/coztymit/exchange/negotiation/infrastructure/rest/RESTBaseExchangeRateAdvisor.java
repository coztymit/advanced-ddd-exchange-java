package pl.coztymit.exchange.negotiation.infrastructure.rest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.application.CurrencyPairApplicationService;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.application.BaseExchangeRateAdvisor;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class RESTBaseExchangeRateAdvisor implements BaseExchangeRateAdvisor {
    @Autowired
    private CurrencyPairApplicationService currencyPairApplicationService;

    @Override
    public Optional<BigDecimal> baseExchangeRate(Currency baseCurrency, Currency targetCurrency) {

        BaseExchangeRateFeignClient client = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .target(BaseExchangeRateFeignClient.class, "http://localhost:8080");
        BaseExchangeRateFeignClient.CurrencyPairResponse currencyPair = client.getCurrencyPair(baseCurrency.toString(), targetCurrency.toString());
        if(currencyPair == null){
            return Optional.empty();
        }
        if(currencyPair.getAdjustedExchangeRate() != null){
            return Optional.of(currencyPair.getAdjustedExchangeRate());
        }else if(currencyPair.getBaseExchangeRate() != null){
            return Optional.of(currencyPair.getBaseExchangeRate());
        }else{
            return Optional.empty();
        }
    }
}
