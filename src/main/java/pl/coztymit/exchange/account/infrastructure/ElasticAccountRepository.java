package pl.coztymit.exchange.account.infrastructure;


import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.*;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;
@Repository
public class ElasticAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> find(AccountNumber accountNumber) {
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

    @Override
    public List<WalletData> findAllByTraderNumber(TraderNumber traderNumber) {
        return null;
    }
}
