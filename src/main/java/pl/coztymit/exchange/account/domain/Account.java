package pl.coztymit.exchange.account.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletNotFoundException;
import pl.coztymit.exchange.account.domain.policy.TransactionLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WalletsLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WithoutTransactionLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WithoutWalletsLimitPolicy;
import pl.coztymit.exchange.account.domain.trader.Trader;
import pl.coztymit.exchange.kernel.Currency;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "accounts")
public class Account {
    @Transient
    private int cardTransactionDailyLimit = 1;

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "account_id"))
    @EmbeddedId
    private AccountId accountId;

    @Embedded
    private Trader trader;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id", nullable = false)
    private List<Wallet> wallets;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id", nullable = false)
    private List<Transaction> transactions;

    private Account() {
    }

    Account(AccountId accountId, Trader trader) {
        this.accountId = accountId;
        this.trader = trader;
        this.transactions = new ArrayList<>();
        this.wallets = new ArrayList<>();
        wallets.add(new Wallet(Funds.ZERO_PLN));
    }

    public void depositFunds(Funds funds, TransactionType transactionType) throws WalletsLimitExceededException, TransactionLimitExceededException {
        TransactionLimitPolicy transactionLimitPolicy = new WithoutTransactionLimitPolicy();
        //Zadanie - wprowadzić politykę na ilość transakcji dziennie

        if(!transactionLimitPolicy.withinTheLimit(funds)) {
            throw new TransactionLimitExceededException("Transaction limit exceeded");
        }

        Optional<Wallet> optionalWalletToDeposit = wallets.stream()
                .filter(wallet -> wallet.isSameCurrency(funds))
                .findFirst();

        if(!optionalWalletToDeposit.isPresent()) {
            WalletsLimitPolicy walletsLimitPolicy = new WithoutWalletsLimitPolicy();
            if(walletsLimitPolicy.isWalletsLimitExceeded(wallets.size())){
                throw new WalletsLimitExceededException();
            }
            wallets.add(new Wallet(funds));
        }else{
            optionalWalletToDeposit.get().addFunds(funds);
        }

        this.transactions.add(new Transaction(transactionType, funds));
    }

    public void withdrawFunds(Funds funds, TransactionType transactionType) throws InsufficientFundsException, TransactionLimitExceededException {
        TransactionLimitPolicy limitPolicy = new WithoutTransactionLimitPolicy();
        if(!limitPolicy.withinTheLimit(funds)) {
            throw new TransactionLimitExceededException("Transaction limit exceeded");
        }
        Wallet walletToWithdraw = wallets.stream()
                .filter(wallet -> wallet.isSameCurrency(funds))
                .findFirst().orElseThrow(WalletNotFoundException::new);

        walletToWithdraw.withdrawFunds(funds);
        this.transactions.add(new Transaction(transactionType, funds));

    }

    public void exchangeCurrency(Funds currencyToBuy, ExchangeRate exchangeRate, TransactionType transactionType) throws InsufficientFundsException {
        //TODO Zadanie 2
        Funds currencyToSell = exchangeRate.calculate(currencyToBuy);

        Wallet fromWallet = wallets.stream()
                    .filter(wallet -> wallet.isSameCurrency(currencyToSell))
                    .findFirst().orElseThrow(WalletNotFoundException::new);

        Wallet toWallet = wallets.stream()
                .filter(wallet -> wallet.isSameCurrency(currencyToBuy))
                .findFirst().orElseThrow(WalletNotFoundException::new);

        fromWallet.withdrawFunds(currencyToSell);
        toWallet.addFunds(currencyToBuy);
        this.transactions.add(new Transaction(transactionType, currencyToBuy));
    }

    public AccountId accountId() {
        return accountId;
    }

    private boolean exhaustedTransactionLimitForToday(TransactionType transactionType){
        if(transactionType.equals(TransactionType.CARD) &&  countDailyTransactionByType(TransactionType.CARD) <= cardTransactionDailyLimit){
            return false;
        }
        return true;
    }

    private long countDailyTransactionByType(TransactionType transactionType){
        return transactions.stream()
                .filter(trans -> trans.type().equals(transactionType) && trans.transactionDate().isItTheSameDay(LocalDateTime.now()))
                .count();
    }
}
