package pl.coztymit.exchange.quoting.domain;

import pl.coztymit.exchange.kernel.Currency;

import java.util.Optional;

public interface ExchangeRateAdvisor {

    Optional<ExchangeRate> exchangeRate(Requester requester, MoneyToExchange moneyToExchange, Currency currencyToSell, Currency currencyToBuy);
}
