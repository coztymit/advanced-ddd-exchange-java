package pl.coztymit.exchange.account.application;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public record WithdrawFundsCommand (String traderNumber, BigDecimal fundsToWithdraw, Currency currency) {
}
