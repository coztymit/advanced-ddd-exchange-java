package pl.coztymit.exchange.account.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.account.domain.exception.InsufficientFundsException;
import pl.coztymit.exchange.kernel.Currency;

import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "wallet_id"))
    private WalletId walletId;

    @AttributeOverrides({
            @AttributeOverride(name = "value.value", column = @Column(name = "fund_value")),
            @AttributeOverride(name = "value.currency.value", column = @Column(name = "fund_currency"))
    })
    private Funds funds;

    private Wallet(){
    }

    Wallet (Funds funds){
        this.walletId = new WalletId(UUID.randomUUID());
        this.funds = funds;
    }

    boolean isSameCurrency(Funds funds){
        return this.funds.isSameCurrency(funds);
    }
    void addFunds(Funds funds){
        this.funds = this.funds.addFunds(funds);
    }
    void withdrawFunds(Funds funds) throws InsufficientFundsException {
        this.funds = this.funds.withdrawFunds(funds);
    }
}
