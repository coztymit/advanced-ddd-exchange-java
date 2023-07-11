package pl.coztymit.exchange.negotiation.domain.policy;

import org.springframework.stereotype.Component;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

@Component
public class MoreThan500EURAutomaticApprovePolicy implements NegotiationAutomaticApprovePolicy {
    private static final Money MIN_AMOUNT = new Money(new BigDecimal(500), new Currency("EUR"));
    private static final BigDecimal MAX_PERCENT = new BigDecimal(3);

    @Override
    public boolean shouldApprove(Money proposedExchangeAmount, BigDecimal percent) {
        return proposedExchangeAmount.isMoreOrEquals(MIN_AMOUNT) && percent.compareTo(new BigDecimal(3)) < 0;
    }

    @Override
    public boolean isApplicable(Money proposedExchangeAmount) {
        return proposedExchangeAmount.theSameCurrency(new Currency("EUR"));
    }
}
