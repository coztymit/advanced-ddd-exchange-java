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
public class APPBaseExchangeRateAdvisor implements BaseExchangeRateAdvisor {
    @Autowired
    private CurrencyPairApplicationService currencyPairApplicationService;

    @Override
    public Optional<BigDecimal> baseExchangeRate(Currency baseCurrency, Currency targetCurrency) {
        ExchangeRatesFeignClient client = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ExchangeRatesFeignClient.class, "https://v6.exchangerate-api.com/v6/86c982b631b2df47540aabc4");

        ExchangeRatesFeignClient.ExchangeRateResponse response = client.getConversionRate(baseCurrency.toString(), targetCurrency.toString());

        return Optional.of((response.getConversion_rate()));
    }
}
