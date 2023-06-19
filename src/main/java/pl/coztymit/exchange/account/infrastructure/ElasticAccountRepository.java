package pl.coztymit.exchange.account.infrastructure;


import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.Account;
import pl.coztymit.exchange.account.domain.AccountId;
import pl.coztymit.exchange.account.domain.AccountRepository;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.Optional;
@Repository
public class ElasticAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> find(AccountId accountId) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public boolean isThereAccountFor(IdentityId identityId) {
        return false;
    }

    @Override
    public Optional<Account> findAccountFor(TraderNumber traderNumber) {
        return Optional.empty();
    }
}
