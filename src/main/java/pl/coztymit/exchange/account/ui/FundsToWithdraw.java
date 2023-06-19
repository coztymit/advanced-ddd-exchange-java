package pl.coztymit.exchange.account.ui;

import java.math.BigDecimal;

public record FundsToWithdraw (BigDecimal value, String currency) {
}
