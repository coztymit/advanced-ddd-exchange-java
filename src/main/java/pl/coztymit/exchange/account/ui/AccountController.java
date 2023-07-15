package pl.coztymit.exchange.account.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.account.application.*;
import pl.coztymit.exchange.account.domain.WalletData;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.kernel.Money;

import java.util.List;
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
            DepositFundsByCardCommand depositFundsByCardCommand = new DepositFundsByCardCommand(traderNumber, fundsToDeposit.getValue(), new Currency(fundsToDeposit.getCurrency()));
            return accountService.depositFundsByCard(depositFundsByCardCommand);
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new DepositFundsStatus(e.getMessage());
        }
    }

    @PostMapping("/deposit/{accountId}")
    public DepositFundsStatus depositFunds(@PathVariable UUID accountId, @RequestBody FundsToDeposit fundsToDeposit) {
        try {
            DepositFundCommand depositFundCommand = new DepositFundCommand(accountId, fundsToDeposit.getValue(), new Currency(fundsToDeposit.getCurrency()));
            return accountService.depositFunds(depositFundCommand);
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
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new BuyCurrencyStatus(e.getMessage());
        }
    }

    @PostMapping("/withdraw/{traderNumber}")
    public WithdrawStatus withdrawFunds(@PathVariable String traderNumber, @RequestBody FundsToWithdraw fundsToWithdraw) {
        try {
            WithdrawFundsCommand withdrawFundsCommand = new WithdrawFundsCommand(traderNumber, fundsToWithdraw.value(), new Currency(fundsToWithdraw.currency()));
            return accountService.withdrawFunds(withdrawFundsCommand)  ;
        } catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new WithdrawStatus(e.getMessage());
        }
    }

    @PostMapping("/transfer/{fromAccountId}/{toAccountId}")
    public TransferFundsStatus transferFundsBetweenAccount(@PathVariable UUID fromAccountId, @PathVariable UUID toAccountId, @RequestBody TransferFundRequest transferFundRequest) {
        try {
            TransferFundsBetweenAccountCommand transferFundsBetweenAccountCommand = new TransferFundsBetweenAccountCommand(fromAccountId, toAccountId, transferFundRequest.getAmount(), transferFundRequest.getCurrency());
            return accountService.transferFundsBetweenAccount(transferFundsBetweenAccountCommand);
        }catch (RuntimeException e) {
            LOG.error("Undefined Exception", e);
            return new TransferFundsStatus(e.getMessage());
        }
    }

    @GetMapping("/{traderNumber}/wallets")
    public List<WalletData> getWalletDataByTraderNumber(@PathVariable String traderNumber) {
        return accountService.getAllWalletsForTrader(traderNumber);
    }

}