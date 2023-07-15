package pl.coztymit.exchange.account.infrastructure;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coztymit.exchange.account.domain.Account;
import pl.coztymit.exchange.account.domain.AccountFactory;
import pl.coztymit.exchange.account.domain.AccountNumber;
import pl.coztymit.exchange.account.domain.AccountStatus;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Transactional
class DBAccountRepositoryTest {
    @Autowired
    private DBAccountRepository repository;

    @Autowired
    private AccountFactory accountFactory;

    @Test
    public void testSave() {
        AccountStatus account = accountFactory.createAccount(IdentityId.generateNewId());
        repository.save(account.account());
        Optional<Account> account1 = repository.find(new AccountNumber(UUID.fromString(account.accountNumber())));
    }
}