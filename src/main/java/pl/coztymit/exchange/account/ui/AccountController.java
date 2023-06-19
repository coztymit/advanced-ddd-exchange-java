package pl.coztymit.exchange.account.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.account.application.*;
import pl.coztymit.exchange.account.domain.exception.AccountNotFoundException;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private Log LOG = LogFactory.getLog(AccountController.class);
    private AccountApplicationService accountService;

    @Autowired
    public AccountController(AccountApplicationService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public CreateAccountStatus createAccount(@RequestBody IdentityId identityId) {
        return accountService.createAccount(identityId);
    }

    @PostMapping("/deposit/card/{traderNumber}")
    public DepositFundsStatus depositFundsByCard(@PathVariable String traderNumber, @RequestBody FundsToDeposit fundsToDeposit) {
        try {
            return accountService.depositFundsByCard(traderNumber, fundsToDeposit.getValue(), new Currency(fundsToDeposit.getCurrency()));
        } catch (AccountNotFoundException e) {
            LOG.error("Account Not Found", e);
            return DepositFundsStatus.ACCOUNT_NOT_FOUND;
        } catch (WalletsLimitExceededException e) {
            LOG.error("Wallets Limit Exceeded", e);
            return DepositFundsStatus.WALLETS_LIMIT_EXCEEDED;
        } catch (TransactionLimitExceededException e) {
            LOG.error("Transaction Limit Exceeded", e);
            return DepositFundsStatus.TRANSACTION_LIMIT_EXCEEDED;
        }catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new DepositFundsStatus(e.getMessage());
        }
    }

    @PostMapping("/deposit/{accountId}")
    public DepositFundsStatus depositFunds(@PathVariable UUID accountId, @RequestBody FundsToDeposit fundsToDeposit) {
        try {

            return accountService.depositFunds(accountId, fundsToDeposit.getValue(), new Currency(fundsToDeposit.getCurrency()));

        } catch (AccountNotFoundException e) {
            LOG.error("Account Not Found", e);
            return DepositFundsStatus.ACCOUNT_NOT_FOUND;
        } catch (WalletsLimitExceededException e) {
            LOG.error("Wallets Limit Exceeded", e);
            return DepositFundsStatus.WALLETS_LIMIT_EXCEEDED;
        } catch (TransactionLimitExceededException e) {
            LOG.error("Transaction Limit Exceeded", e);
            return DepositFundsStatus.TRANSACTION_LIMIT_EXCEEDED;
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new DepositFundsStatus(e.getMessage());
        }
    }

    @PostMapping("/buyCurrency/{traderNumber}")
    public BuyCurrencyStatus buyCurrency(@PathVariable String traderNumber, @RequestBody FundToBuy fundToBuy) {
        try {
            Money moneyToBuy = new Money(fundToBuy.value(), new Currency(fundToBuy.currency()));
            return accountService.buyCurrency(traderNumber, moneyToBuy, fundToBuy.exchangeRateCommand());
        } catch (AccountNotFoundException e) {
            LOG.error("Account Not Found", e);
            return BuyCurrencyStatus.ACCOUNT_NOT_FOUND;
        }catch (InsufficientFundsException e) {
            LOG.error("Insufficient funds", e);
            return BuyCurrencyStatus.INSUFFICIENT_FUNDS;
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new BuyCurrencyStatus(e.getMessage());
        }
    }

    @PostMapping("/withdraw/{traderNumber}")
    public WithdrawStatus withdrawFunds(@PathVariable String traderNumber, @RequestBody FundsToWithdraw fundsToWithdraw) {
        try {
            return accountService.withdrawFunds(traderNumber, fundsToWithdraw.value(), new Currency(fundsToWithdraw.currency()))  ;
        } catch (AccountNotFoundException e) {
            LOG.error("Account Not Found", e);
            return WithdrawStatus.ACCOUNT_NOT_FOUND;
        }catch (InsufficientFundsException e) {
            LOG.error("Insufficient funds", e);
            return WithdrawStatus.INSUFFICIENT_FUNDS;
        } catch (TransactionLimitExceededException e) {
            LOG.error("Transaction limit exceeded", e);
            return WithdrawStatus.TRANSACTION_LIMIT_EXCEEDED;
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new WithdrawStatus(e.getMessage());
        }
    }

    @PostMapping("/transfer/{fromAccountId}/{toAccountId}")
    public TransferFundsStatus transferFundsBetweenAccount(@PathVariable UUID fromAccountId, @PathVariable UUID toAccountId, @RequestBody TransferFoundRequest transferFoundRequest) throws AccountNotFoundException {
        try {
            return accountService.transferFundsBetweenAccount(fromAccountId, toAccountId, transferFoundRequest.getAmount(), transferFoundRequest.getCurrency());
        } catch (InsufficientFundsException e) {
            LOG.error("Insufficient funds", e);
            return TransferFundsStatus.INSUFFICIENT_FUNDS;
        } catch (WalletsLimitExceededException e) {
            LOG.error("Wallets limit exceeded", e);
            return TransferFundsStatus.WALLETS_LIMIT_EXCEEDED;
        } catch (TransactionLimitExceededException e) {
            LOG.error("Transaction limit exceeded", e);
            return TransferFundsStatus.TRANSACTION_LIMIT_EXCEEDED;
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new TransferFundsStatus(e.getMessage());
        }
    }
}