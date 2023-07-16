package pl.coztymit.exchange.negotiation.domain.policy;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.ProposedExchangeAmount;

import java.math.BigDecimal;

@Component
public class MoreThan500EURAutomaticApprovePolicy implements NegotiationAutomaticApprovePolicy {
    private static final ProposedExchangeAmount MIN_AMOUNT = new ProposedExchangeAmount(new BigDecimal(500), new Currency("EUR"));

    @Override
    public boolean shouldApprove(ProposedExchangeAmount proposedExchangeAmount, BigDecimal percent) {
        return proposedExchangeAmount.isMoreOrEquals(MIN_AMOUNT) && percent.compareTo(new BigDecimal(10)) < 0;
    }

    @Override
    public boolean isApplicable(ProposedExchangeAmount proposedExchangeAmount) {
        return proposedExchangeAmount.theSameCurrency(new Currency("EUR"));
    }
}
