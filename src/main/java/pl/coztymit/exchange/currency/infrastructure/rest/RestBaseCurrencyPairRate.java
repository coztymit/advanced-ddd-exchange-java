package pl.coztymit.exchange.currency.infrastructure.rest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.BaseCurrencyPairRate;
import pl.coztymit.exchange.currency.domain.ExchangeRate;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.infrastructure.rest.ExchangeRatesFeignClient;

import java.util.Optional;

@Component
public class RestBaseCurrencyPairRate implements BaseCurrencyPairRate {
    @Override
    public Optional<ExchangeRate> baseRateFor(Currency baseCurrency, Currency targetCurrency) {
        ExchangeRatesFeignClient client = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ExchangeRatesFeignClient.class, "https://v6.exchangerate-api.com/v6/86c982b631b2df47540aabc4");

        ExchangeRatesFeignClient.ExchangeRateResponse response = client.getConversionRate(baseCurrency.toString(), targetCurrency.toString());

        return Optional.of(new ExchangeRate(response.getConversion_rate()));
        //TODO pobieranie kursu z zewnętrznego serwisu np. NBP
    }
}
