package pl.coztymit.exchange.negotiation.domain.policy;

import pl.coztymit.exchange.negotiation.domain.ProposedExchangeAmount;

import java.math.BigDecimal;

public interface NegotiationAutomaticApprovePolicy {
    boolean shouldApprove(ProposedExchangeAmount proposedExchangeAmount, BigDecimal percent);
    boolean isApplicable(ProposedExchangeAmount proposedExchangeAmount);
}
