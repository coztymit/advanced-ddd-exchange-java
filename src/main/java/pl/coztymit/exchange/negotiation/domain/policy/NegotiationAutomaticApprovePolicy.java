package pl.coztymit.exchange.negotiation.domain.policy;

import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

public interface NegotiationAutomaticApprovePolicy {
    boolean shouldApprove(Money proposedExchangeAmount, BigDecimal percent);
    boolean isApplicable(Money proposedExchangeAmount);
}
