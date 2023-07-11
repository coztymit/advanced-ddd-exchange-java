package pl.coztymit.exchange.negotiation.application;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.Status;

import java.math.BigDecimal;

public record CreateNegotiationCommand(TraderNumber traderNumber, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount, BigDecimal proposedRate) {

}
