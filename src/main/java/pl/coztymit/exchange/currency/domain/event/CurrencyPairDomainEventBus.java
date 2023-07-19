package pl.coztymit.exchange.currency.domain.event;

public interface CurrencyPairDomainEventBus {
    void post(CurrencyPairCreated currencyPairCreated);
    void post(CurrencyPairExchangeRateAdjusted currencyPairExchangeRateAdjusted);
    void post(CurrencyPairDeactivated currencyPairDeactivated);
    void post(CurrencyPairActivated currencyPairActivated);
}
