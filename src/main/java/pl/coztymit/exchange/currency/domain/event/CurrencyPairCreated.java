package pl.coztymit.exchange.currency.domain.event;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public record CurrencyPairCreated (Currency baseCurrency, Currency targetCurrency, BigDecimal rate){
}
