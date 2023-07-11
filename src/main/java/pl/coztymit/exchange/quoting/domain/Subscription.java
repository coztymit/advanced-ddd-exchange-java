package pl.coztymit.exchange.quoting.domain;

import pl.coztymit.exchange.account.domain.trader.TraderNumber;

public class Subscription {
    private TraderNumber traderNumber;
    private String subscriptionLevel;

    public boolean subscriptionEquals(String subscriptionLevel) {
        return this.subscriptionLevel.equals(subscriptionLevel);
    }
}
