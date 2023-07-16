package pl.coztymit.exchange.currency.infrastructure.rest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.BaseCurrencyPairRate;
import pl.coztymit.exchange.currency.domain.ExchangeRate;
import pl.coztymit.exchange.kernel.Currency;

import java.util.Optional;

@Component
public class RestBaseCurrencyPairRate implements BaseCurrencyPairRate {
    @Override
    public Optional<ExchangeRate> baseRateFor(Currency baseCurrency, Currency targetCurrency) {
        RESTBaseCurrencyPairRateFeignClient client = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(RESTBaseCurrencyPairRateFeignClient.class, "https://v6.exchangerate-api.com/v6/86c982b631b2df47540aabc4");

        RESTBaseCurrencyPairRateFeignClient.ExchangeRateResponse response = client.getConversionRate(baseCurrency.toString(), targetCurrency.toString());

        return Optional.of(new ExchangeRate(response.getConversion_rate()));
    }
}
