package pl.coztymit.exchange.account.domain;


import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> find(AccountNumber accountNumber);

    void save(Account account);

    boolean isThereAccountFor(IdentityId identityId);

    Optional<Account> findAccountFor(TraderNumber traderNumber);

    List<WalletData> findAllByTraderNumber(TraderNumber traderNumber);

}
