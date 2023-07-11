package pl.coztymit.exchange.quoting.domain.policy;

import pl.coztymit.exchange.quoting.domain.ExpirationDate;

public class OneHourQuoteExpirationDatePolicy implements QuoteExpirationDatePolicy {

        @Override
        public ExpirationDate generateExpirationDate() {
            return ExpirationDate.oneHourExpirationDate();
        }
}
