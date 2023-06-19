package pl.coztymit.exchange.accounting.domain.policy;

public interface LineLimitPolicy {
    boolean lessOrEqualsLimit(int positionsCount);
}
