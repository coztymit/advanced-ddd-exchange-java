package pl.coztymit.exchange.account.domain;


import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> find(AccountId accountId);

    void save(Account account);

    boolean isThereAccountFor(IdentityId identityId);

    Optional<Account> findAccountFor(TraderNumber traderNumber);

}
