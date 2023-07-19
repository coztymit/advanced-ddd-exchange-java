package pl.coztymit.exchange.account.domain.trader;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import pl.coztymit.exchange.account.domain.TraderNumber;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.function.Function;

public class Trader {

    @AttributeOverride(name = "value",
                    column = @Column(name = "trader_number"))
    private TraderNumber number;

    @AttributeOverride(name = "uuid",
            column = @Column(name = "identity_id"))
    private IdentityId identityId;

    private Trader() {
    }
    public Trader(TraderNumber number, IdentityId identityId) {
        this.number = number;
        this.identityId = identityId;
    }
    public <R> R identity(Function<IdentityId, R> converter) {
        return converter.apply(this.identityId);
    }
}
