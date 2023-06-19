package pl.coztymit.exchange.account.domain.policy;

public interface WalletsLimitPolicy {
    boolean isWalletsLimitExceeded(int walletsQuantity);
}
