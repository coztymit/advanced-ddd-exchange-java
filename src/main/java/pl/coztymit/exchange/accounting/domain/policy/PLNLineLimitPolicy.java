package pl.coztymit.exchange.accounting.domain.policy;

public class PLNLineLimitPolicy implements LineLimitPolicy {

    private int positionLimit = 10;

    @Override
    public boolean lessOrEqualsLimit(int positionsCount) {
        return positionLimit >= positionsCount;
    }
}
