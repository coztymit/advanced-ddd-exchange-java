package pl.coztymit.exchange.accounting.domain.policy;

public class EURLineLimitPolicy implements LineLimitPolicy {

    private int positionLimit = 5;

    @Override
    public boolean lessOrEqualsLimit(int positionsCount) {
        return positionLimit >= positionsCount;
    }
}
