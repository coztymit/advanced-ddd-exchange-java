package pl.coztymit.exchange.infrastructure.eventbroker.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.event.*;
import pl.coztymit.exchange.negotiation.application.SupportedCurrencyApplicationService;

@Component
public class CurrencyPairEventBroker implements CurrencyPairDomainEventBus {

    @Autowired
    private SupportedCurrencyApplicationService supportedCurrencyApplicationService;

    @Override
    public void post(CurrencyPairCreated currencyPairCreated) {
        supportedCurrencyApplicationService.addSupportedCurrency(currencyPairCreated.baseCurrency(), currencyPairCreated.targetCurrency(), currencyPairCreated.rate());
    }

    @Override
    public void post(CurrencyPairExchangeRateAdjusted currencyPairExchangeRateAdjusted) {
        supportedCurrencyApplicationService.adjustCurrencyPair(currencyPairExchangeRateAdjusted.baseCurrency(), currencyPairExchangeRateAdjusted.targetCurrency(), currencyPairExchangeRateAdjusted.adjustedRate());
    }

    @Override
    public void post(CurrencyPairDeactivated currencyPairDeactivated) {
        supportedCurrencyApplicationService.deactivateCurrencyPair(currencyPairDeactivated.baseCurrency(), currencyPairDeactivated.targetCurrency());
    }

    @Override
    public void post(CurrencyPairActivated currencyPairActivated) {
        supportedCurrencyApplicationService.activateCurrencyPair(currencyPairActivated.baseCurrency(), currencyPairActivated.targetCurrency() );
    }
}
