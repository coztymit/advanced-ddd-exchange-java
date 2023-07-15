package pl.coztymit.exchange.account.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletNotFoundException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;
import pl.coztymit.exchange.account.domain.policy.TransactionLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WalletsLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WithoutTransactionLimitPolicy;
import pl.coztymit.exchange.account.domain.policy.WithoutWalletsLimitPolicy;
import pl.coztymit.exchange.account.domain.trader.Trader;

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
    @AttributeOverride(name = "uuid", column = @Column(name = "account_number"))
    @EmbeddedId
    private AccountNumber accountNumber;

    @Embedded
    private Trader trader;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_number", nullable = false)
    private List<Wallet> wallets;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_number", nullable = false)
    private List<Transaction> transactions;

    private Account() {
    }

    Account(AccountNumber accountNumber, Trader trader) {
        this.accountNumber = accountNumber;
        this.trader = trader;
        this.transactions = new ArrayList<>();
        this.wallets = new ArrayList<>();
        wallets.add(new Wallet(Funds.ZERO_PLN));
    }

    public void depositFunds(Funds funds, TransactionType transactionType) throws WalletsLimitExceededException, TransactionLimitExceededException {
        TransactionLimitPolicy transactionLimitPolicy = new WithoutTransactionLimitPolicy();

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
        Funds currencyToSell = exchangeRate.calculate(currencyToBuy);

        Wallet fromWallet = wallets.stream()
                    .filter(wallet -> wallet.isSameCurrency(currencyToSell))
                    .findFirst().orElseThrow(WalletNotFoundException::new);

        Optional<Wallet> optionalToWallet = wallets.stream()
                .filter(wallet -> wallet.isSameCurrency(currencyToBuy))
                .findFirst();

        Wallet toWallet = optionalToWallet.orElseGet(() -> {
            Wallet wallet = new Wallet(currencyToBuy);
            wallets.add(wallet);
            return wallet;
        });

        fromWallet.withdrawFunds(currencyToSell);
        toWallet.addFunds(currencyToBuy);
        this.transactions.add(new Transaction(transactionType, currencyToBuy));
    }

    public AccountNumber accountNumber() {
        return accountNumber;
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
