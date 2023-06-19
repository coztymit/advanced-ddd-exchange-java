package pl.coztymit.exchange.account.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.account.domain.exception.AccountNotFoundException;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.account.domain.*;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Service
public class AccountApplicationService {

    private Log LOG = LogFactory.getLog(AccountApplicationService.class);

    private AccountRepository accountRepository;
    private AccountFactory accountFactory;

    @Autowired
    public AccountApplicationService(@Qualifier("DBAccountRepository") AccountRepository accountRepository, AccountFactory accountFactory) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
    }

    @Transactional
    public CreateAccountStatus createAccount(IdentityId identityId) {
        requireNonNull(identityId, "identityId cannot be null");

        AccountStatus accountStatus = accountFactory.createAccount(identityId);
        if (accountStatus.isSuccess()) {
            accountRepository.save(accountStatus.account());
            return CreateAccountStatus.success(accountStatus.status(), identityId, accountStatus.accountId(), accountStatus.traderNumber());
        }
        return CreateAccountStatus.createFailAccountStatus(accountStatus.status(), identityId);
    }

    @Transactional
    public DepositFundsStatus depositFundsByCard(String traderNumber, BigDecimal fundsToDeposit, Currency currency) throws AccountNotFoundException, WalletsLimitExceededException, TransactionLimitExceededException {

        Optional<Account> optionalAccount = accountRepository.findAccountFor(new TraderNumber(traderNumber));
        Account account = optionalAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));

        depositFund(account, fundsToDeposit, TransactionType.CARD, currency);

        return DepositFundsStatus.success(account.accountId().toString());
    }

    @Transactional
    public DepositFundsStatus depositFunds(UUID accountId, BigDecimal fundsToDeposit, Currency currency) throws AccountNotFoundException, WalletsLimitExceededException, TransactionLimitExceededException {
        Optional<Account> optionalAccount = accountRepository.find(new AccountId(accountId));
        Account account = optionalAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));
        depositFund(account, fundsToDeposit, TransactionType.DEPOSIT, currency);

        return DepositFundsStatus.success(account.accountId().toString());
    }

    @Transactional
    public BuyCurrencyStatus buyCurrency(String traderNumber, Money currencyToBuy, ExchangeRateCommand exchangeRateCommand) throws AccountNotFoundException, InsufficientFundsException {

        ExchangeRate exchangeRate = new ExchangeRate(
                new Currency(exchangeRateCommand.currencyToSell()),
                new Currency(exchangeRateCommand.currencyToBuy()),
                exchangeRateCommand.rate());

        Optional<Account> optionalAccount = accountRepository.findAccountFor(new TraderNumber(traderNumber));
        Account account = optionalAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.exchangeCurrency(new Funds(currencyToBuy), exchangeRate, TransactionType.CURRENCY_EXCHANGE);

        accountRepository.save(account);
        return BuyCurrencyStatus.BUY_SUCCESS;
    }

    @Transactional
    public WithdrawStatus withdrawFunds(String traderId, BigDecimal fundsToWithdraw, Currency currency) throws AccountNotFoundException, TransactionLimitExceededException, InsufficientFundsException {
        Optional<Account> optionalAccount = accountRepository.findAccountFor(new TraderNumber(traderId));
        Account account = optionalAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.withdrawFunds(new Funds(fundsToWithdraw, currency), TransactionType.WITHDRAW);

        accountRepository.save(account);
        return WithdrawStatus.WITHDRAW_SUCCESS;
    }

    @Transactional
    public TransferFundsStatus transferFundsBetweenAccount(UUID fromAccountId, UUID toAccountId, BigDecimal fundsToTransfer, String currency) throws AccountNotFoundException, TransactionLimitExceededException, InsufficientFundsException, WalletsLimitExceededException {
        //TODO Zadanie 1
        Optional<Account> optionalFromAccount = accountRepository.find(new AccountId(fromAccountId));
        Account fromAccount = optionalFromAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Optional<Account> optionalToAccount = accountRepository.find(new AccountId(toAccountId));
        Account toAccount = optionalToAccount.orElseThrow(() -> new AccountNotFoundException("Account not found"));

        new TransferFundsDomainService().transferFunds(fromAccount, toAccount,  new Funds(fundsToTransfer, new Currency(currency)));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return TransferFundsStatus.TRANSFER_SUCCESS;
    }

    private void depositFund(Account account, BigDecimal fundsToDeposit, TransactionType transactionType, Currency currency) throws WalletsLimitExceededException, TransactionLimitExceededException {
        account.depositFunds(new Funds(fundsToDeposit, currency), transactionType);
        accountRepository.save(account);
    }
}
