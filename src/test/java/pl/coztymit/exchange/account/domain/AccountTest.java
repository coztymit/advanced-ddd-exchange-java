package pl.coztymit.exchange.account.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;
import pl.coztymit.exchange.account.domain.trader.Trader;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AccountTest {

    private Trader trader;
    private Funds funds;
    private Account account;

    @BeforeEach
    public void setUp() {
        trader = new Trader(new TraderNumber("ABC-15-2025-123"), IdentityId.generateNewId());
        account = new Account(AccountNumber.generateNewId(),trader);
        funds = new Funds(new BigDecimal("100.00"));

    }

    @Test
    public void shouldAddFundsToWallet() throws TransactionLimitExceededException, WalletsLimitExceededException {
        //given
        Funds fundsToAdd = new Funds(new BigDecimal("100.00"), Currency.PLN);
        //when
        account.depositFunds(funds, TransactionType.CARD);

        //then
        List<Wallet> wallets = ( List<Wallet>) ReflectionTestUtils.getField(account, "wallets");
        Wallet wallet = wallets.stream().filter(x -> x.isSameCurrency(fundsToAdd)).findFirst().orElseThrow();
        Funds funds = (Funds) ReflectionTestUtils.getField(wallet, "funds");

        assertTrue(funds.equals(fundsToAdd));
    }
    @Test
    public void shouldWithdrawFundsFromWallet() throws TransactionLimitExceededException, InsufficientFundsException, WalletsLimitExceededException {
        //given
        Funds fundsToWithdraw = new Funds(new BigDecimal("100.00"), Currency.PLN);
        Funds fundInWallet = new Funds(new BigDecimal("150.00"), Currency.PLN);
        account.depositFunds(fundInWallet, TransactionType.CARD);
        //when
        account.withdrawFunds(funds, TransactionType.CARD);

        //then
        List<Wallet> wallets = ( List<Wallet>) ReflectionTestUtils.getField(account, "wallets");
        Wallet wallet = wallets.stream().filter(x -> x.isSameCurrency(fundsToWithdraw)).findFirst().orElseThrow();
        Funds funds = (Funds) ReflectionTestUtils.getField(wallet, "funds");

        assertTrue(funds.equals(new Funds(new BigDecimal("50.00"))));
    }
}