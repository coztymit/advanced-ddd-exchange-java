package pl.coztymit.exchange.quoting.domain.policy;

import pl.coztymit.exchange.quoting.domain.ExpirationDate;

public class OneDayQuoteExpirationDatePolicy implements QuoteExpirationDatePolicy {

        @Override
        public ExpirationDate generateExpirationDate() {
            return ExpirationDate.oneDayExpirationDate();
        }
}
