package pl.coztymit.exchange.quoting.application;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Money;

public record PrepareQuoteCommand(TraderNumber traderNumber, Money moneyToExchange,String currencyToSell, String currencyToBuy) {
}
