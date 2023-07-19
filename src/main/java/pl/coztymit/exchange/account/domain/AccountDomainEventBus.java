package pl.coztymit.exchange.account.domain;

import pl.coztymit.exchange.account.domain.events.AccountActivated;

public interface AccountDomainEventBus {
    void post(AccountActivated accountActivated);
}
