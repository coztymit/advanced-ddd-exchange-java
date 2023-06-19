package pl.coztymit.exchange.account.domain.policy;

import pl.coztymit.exchange.account.domain.Funds;

public interface TransactionLimitPolicy {
    boolean withinTheLimit(Funds funds);
}
