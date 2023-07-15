package pl.coztymit.exchange.account.application;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public record DepositFundsByCardCommand (String traderNumber, BigDecimal fundsToDeposit, Currency currency){
}
