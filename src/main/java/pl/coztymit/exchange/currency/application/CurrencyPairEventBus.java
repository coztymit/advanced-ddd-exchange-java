package pl.coztymit.exchange.currency.application;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairCreated;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairDeactivated;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairDomainEventBus;
import pl.coztymit.exchange.currency.domain.event.CurrencyPairExchangeRateAdjusted;

@Component
public class CurrencyPairEventBus implements CurrencyPairDomainEventBus {
    @Override
    public void post(CurrencyPairCreated currencyPairCreated) {

    }

    @Override
    public void post(CurrencyPairExchangeRateAdjusted currencyPairExchangeRateAdjusted) {

    }

    @Override
    public void post(CurrencyPairDeactivated currencyPairDeactivated) {

    }
}
