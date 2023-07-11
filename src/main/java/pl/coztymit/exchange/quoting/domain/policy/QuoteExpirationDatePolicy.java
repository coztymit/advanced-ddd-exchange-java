package pl.coztymit.exchange.quoting.domain.policy;

import pl.coztymit.exchange.quoting.domain.ExpirationDate;

public interface QuoteExpirationDatePolicy {

    ExpirationDate generateExpirationDate();
}
