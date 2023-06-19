package pl.coztymit.exchange.account.domain.policy;

import pl.coztymit.exchange.account.domain.Funds;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;

public class EuroTransactionLimitPolicy implements TransactionLimitPolicy{
    private Funds upperTransactionLimit = new Funds(new BigDecimal("4000"), Currency.EUR);
    private Funds lowerTransactionLimit = new Funds(new BigDecimal("12"), Currency.EUR);
    @Override
    public boolean withinTheLimit(Funds funds) {
        if(funds.lessOrEqualsThan(upperTransactionLimit) && funds.greaterOrEqualsThan(lowerTransactionLimit)){
            return true;
        }
        return false;
    }
}
