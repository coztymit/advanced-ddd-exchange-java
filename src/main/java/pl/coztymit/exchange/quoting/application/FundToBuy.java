package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.account.application.ExchangeRateCommand;

import java.math.BigDecimal;

public record FundToBuy(BigDecimal value, String currency, ExchangeRateCommand exchangeRateCommand) {
}
