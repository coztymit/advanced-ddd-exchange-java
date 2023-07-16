package pl.coztymit.exchange.currency.domain.event;

import pl.coztymit.exchange.currency.domain.CurrencyPairId;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public record CurrencyPairExchangeRateAdjusted (CurrencyPairId currencyPairId, Currency baseCurrency, Currency targetCurrency, BigDecimal adjustedRate){

}
