package pl.coztymit.exchange.quoting.infrastructure.rest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.quoting.domain.ExchangeRateAdvisor;
import pl.coztymit.exchange.quoting.domain.ExchangeRate;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class RestBaseCurrencyExchangeRateAdvisor implements ExchangeRateAdvisor {
    @Override
    public Optional<ExchangeRate> exchangeRate(Currency currencyToSell, Currency currencyToBuy) {

        ExchangeRatesFeignClient client = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ExchangeRatesFeignClient.class, "https://v6.exchangerate-api.com/v6/86c982b631b2df47540aabc4");

        ExchangeRatesFeignClient.ExchangeRateResponse response = client.getConversionRate(currencyToSell.toString(), currencyToBuy.toString());

        return Optional.of(new ExchangeRate(currencyToSell, currencyToBuy, response.getConversion_rate()));
        //TODO pobieranie kursu z zewnętrznego serwisu np. NBP

    }
}
