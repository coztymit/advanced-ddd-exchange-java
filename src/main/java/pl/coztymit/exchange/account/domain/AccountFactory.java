package pl.coztymit.exchange.account.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.account.domain.trader.Trader;

@Component
public class AccountFactory {

    @Autowired
    @Qualifier("DBAccountRepository")
    private AccountRepository accountRepository;

    public AccountStatus createAccount(IdentityId identityId){
        if (accountRepository.isThereAccountFor(identityId)){
            return AccountStatus.createAccountAlreadyExistsStatus();
        }

        AccountNumber accountNumber = AccountNumber.generateNewId();
        TraderNumber traderNumber = TraderNumber.generateNewNumber();
        Trader trader = new Trader(traderNumber, identityId);
        Account account = new Account(accountNumber, trader);

        return AccountStatus.createSuccessAccountStatus(AccountStatus.SUCCESS, account, accountNumber.toString(), traderNumber.toString());
    }
}
