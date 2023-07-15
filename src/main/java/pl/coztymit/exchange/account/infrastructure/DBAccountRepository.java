package pl.coztymit.exchange.account.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.*;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

@Repository
public class DBAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> find(AccountNumber accountNumber) {
        Account account = entityManager.find(Account.class, accountNumber);
        return Optional.ofNullable(account);
    }

    @Override
    public void save(Account account) {
        entityManager.persist(account);
    }

    @Override
    public boolean isThereAccountFor(IdentityId identityId) {
        String queryString = "SELECT a FROM Account a WHERE a.trader.identityId = :identityId";
        TypedQuery<Account> query = entityManager.createQuery(queryString, Account.class);
        query.setParameter("identityId", identityId);
        List<Account> accounts = query.getResultList();
        return !accounts.isEmpty();
    }

    @Override
    public Optional<Account> findAccountFor(TraderNumber traderNumber) {
        String queryString = "SELECT a FROM Account a WHERE a.trader.number = :traderNumber";
        TypedQuery<Account> query = entityManager.createQuery(queryString, Account.class);
        query.setParameter("traderNumber", traderNumber);
        try {
            Account account = query.getSingleResult();
            return Optional.ofNullable(account);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<WalletData> findAllByTraderNumber(TraderNumber traderNumber) {
        String queryString = "SELECT new pl.coztymit.exchange.account.domain.WalletData(w.walletId.value, w.funds.value.value, w.funds.value.currency.value) FROM Account a JOIN a.wallets w WHERE a.trader.number = :number";
        TypedQuery<WalletData> query = entityManager.createQuery(queryString, WalletData.class);
        query.setParameter("number", traderNumber);
        return query.getResultList();
    }


}
