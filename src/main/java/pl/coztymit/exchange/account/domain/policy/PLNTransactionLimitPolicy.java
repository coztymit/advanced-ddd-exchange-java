package pl.coztymit.exchange.account.domain.policy;



import pl.coztymit.exchange.account.domain.Funds;

import java.math.BigDecimal;

public class PLNTransactionLimitPolicy implements TransactionLimitPolicy{
    private Funds upperTransactionLimit = new Funds(new BigDecimal("15000"));
    private Funds lowerTransactionLimit = new Funds(new BigDecimal("50"));
    @Override
    public boolean withinTheLimit(Funds funds) {
        if(funds.lessOrEqualsThan(upperTransactionLimit) && funds.greaterOrEqualsThan(lowerTransactionLimit)){
            return true;
        }
        return false;
    }
}
