package pl.coztymit.exchange.account.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.account.domain.trader.Trader;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;

@Component
public class AccountFactory {

    @Autowired
    @Qualifier("DBAccountRepository")
    private AccountRepository accountRepository;

    public AccountStatus createAccount(IdentityId identityId){
        //Zadanie x - zastosowanie fabryki aby uniknać getterów i setterów
        if (accountRepository.isThereAccountFor(identityId)){
            return AccountStatus.createAccountAlreadyExistsStatus();
        }

        AccountId accountId = AccountId.generateNewId();
        TraderNumber traderNumber = TraderNumber.generateNewNumber();
        Trader trader = new Trader(traderNumber, identityId);
        Account account = new Account(accountId, trader);

        return AccountStatus.createSuccessAccountStatus(AccountStatus.SUCCESS, account, accountId.toString(), traderNumber.toString());
    }
}
