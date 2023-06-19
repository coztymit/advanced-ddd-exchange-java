package pl.coztymit.exchange.account.domain;

import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.account.domain.exception.TransactionLimitExceededException;
import pl.coztymit.exchange.account.domain.exception.WalletsLimitExceededException;

public class TransferFundsDomainService {

    public void transferFunds(Account accountFrom, Account accountTo, Funds founds) throws InsufficientFundsException, WalletsLimitExceededException, TransactionLimitExceededException {
        accountFrom.withdrawFunds(founds, TransactionType.TRANSFER_BETWEEN_ACCOUNTS_WITHDRAW);
        accountTo.depositFunds(founds, TransactionType.TRANSFER_BETWEEN_ACCOUNTS_DEPOSIT);
    }
}
