package pl.coztymit.exchange.quoting.ui;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Money;

public record PrepareQuoteRequest (TraderNumber traderNumber, Money moneyToExchange, String currencyToBuy, String currencyToSell) {

}
