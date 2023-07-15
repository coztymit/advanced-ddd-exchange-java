package pl.coztymit.exchange.account.domain;

import java.util.List;

public class AccountData {
    private final String accountNumber;
    private final String traderNumber;
    private List<WalletData> wallets;

    public AccountData(String accountNumber, String traderNumber, List<WalletData> wallets) {
        this.accountNumber = accountNumber;
        this.traderNumber = traderNumber;
        this.wallets = wallets;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTraderNumber() {
        return traderNumber;
    }

    public List<WalletData> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletData> wallets) {
        this.wallets = wallets;
    }
}
