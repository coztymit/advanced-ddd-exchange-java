package pl.coztymit.exchange.account.domain.policy;

import pl.coztymit.exchange.account.domain.Funds;

public class WithoutTransactionLimitPolicy implements TransactionLimitPolicy{
    @Override
    public boolean withinTheLimit(Funds funds) {
        return true;
    }
}
