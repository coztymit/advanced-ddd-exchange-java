package pl.coztymit.exchange.account.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.coztymit.exchange.account.domain.Account;
import pl.coztymit.exchange.account.domain.AccountId;
import pl.coztymit.exchange.account.domain.AccountRepository;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;
@Repository
public class DBAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> find(AccountId accountId) {
        Account account = entityManager.find(Account.class, accountId);
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
}
