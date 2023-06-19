package pl.coztymit.exchange.account.domain;

import org.junit.jupiter.api.Test;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateTest {

    @Test
    void shouldCalculateCorrectly() {
        Currency sellCurrency = new Currency("USD");
        Currency buyCurrency = new Currency("EUR");
        BigDecimal rate = new BigDecimal("0.82");
        ExchangeRate exchangeRate = new ExchangeRate(sellCurrency, buyCurrency, rate);

        Funds fundToBuy = new Funds(new BigDecimal("100.00"), buyCurrency);
        Funds expectedFundToSell = new Funds(new BigDecimal("82.00"), sellCurrency);

        Funds calculatedFundToSell = exchangeRate.calculate(fundToBuy);
        assertTrue(expectedFundToSell.equals(calculatedFundToSell));
    }

    @Test
    void shouldThrowExceptionWhenDifferentCurrencies() {
        Currency from = new Currency("USD");
        Currency to = new Currency("EUR");
        BigDecimal rate = new BigDecimal("0.82");
        ExchangeRate exchangeRate = new ExchangeRate(from, to, rate);

        Funds funds = new Funds(new BigDecimal("100"), new Currency("GBP"));

        assertThrows(RuntimeException.class, () -> exchangeRate.calculate(funds));
    }
}