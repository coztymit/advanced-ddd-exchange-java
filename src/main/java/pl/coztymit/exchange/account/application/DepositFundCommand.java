package pl.coztymit.exchange.account.application;

import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositFundCommand(UUID accountNumber, BigDecimal fundsToDeposit, Currency currency){

}
