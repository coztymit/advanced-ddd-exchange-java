package pl.coztymit.exchange.account.application;

import java.math.BigDecimal;

public record ExchangeRateCommand(String currencyToBuy, String currencyToSell, BigDecimal rate) {
}
