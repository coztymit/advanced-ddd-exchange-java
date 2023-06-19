package pl.coztymit.exchange.account.domain.policy;

public class WithoutWalletsLimitPolicy implements WalletsLimitPolicy {
    @Override
    public boolean isWalletsLimitExceeded(int walletsQuantity) {
        return false;
    }
}

