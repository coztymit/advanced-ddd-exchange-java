package pl.coztymit.exchange.quoting.domain;


import org.springframework.stereotype.Service;
import pl.coztymit.exchange.quoting.domain.policy.OneDayQuoteExpirationDatePolicy;
import pl.coztymit.exchange.quoting.domain.policy.QuoteExpirationDatePolicy;

@Service
public class ExchangeDomainService {
    public QuoteExpirationDatePolicy determineQuoteExpirationDatePolicy(Requester requester) {
        return new OneDayQuoteExpirationDatePolicy();
    }
}
