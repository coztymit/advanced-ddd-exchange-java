package pl.coztymit.exchange.quoting.domain;

import org.junit.jupiter.api.Test;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateTest {

    @Test
    void shouldCalculateAmountOfEurosForGivenPLNAmountDuringEuroBuying() {

        //given
        ExchangeRate exchangeRate = new ExchangeRate(Currency.PLN, Currency.EUR, new Rate(new BigDecimal("0.22")));

        //when
        MoneyExchanged calculate = exchangeRate.exchange(new MoneyToExchange(new BigDecimal("100.00"), Currency.PLN));

        //then
        assertTrue(calculate.equals(new Money(new BigDecimal("22.00"), Currency.EUR)));
    }

    @Test
    void shouldCalculateAmountOfPLNForGivenEuroAmountDuringEuroBuying() {
        //given
        ExchangeRate exchangeRate = new ExchangeRate(Currency.PLN, Currency.EUR, new Rate(new BigDecimal("0.22")));

        //when
        MoneyExchanged calculate = exchangeRate.exchange(new MoneyToExchange(new BigDecimal("100.00"), Currency.EUR));

        //then
        assertTrue(calculate.equals(new Money(new BigDecimal("454.55"), Currency.PLN)));
    }

    @Test
    void shouldCalculateAmountOfEurosForGivenPLNAmountDuringPLNBuying() {

        //given
        ExchangeRate exchangeRate = new ExchangeRate(Currency.EUR, Currency.PLN, new Rate(new BigDecimal("4.80")));

        //when
        MoneyExchanged calculate = exchangeRate.exchange(new MoneyToExchange(new BigDecimal("100.00"), Currency.PLN));

        //then
        assertTrue(calculate.equals(new Money(new BigDecimal("22.83"), Currency.EUR)));
    }

    @Test
    void shouldCalculateAmountOfPLNForGivenEURAmountDuringPLNBuying() {

        //given
        ExchangeRate exchangeRate = new ExchangeRate(Currency.EUR, Currency.PLN, new Rate(new BigDecimal("4.80")));

        //when
        MoneyExchanged calculate = exchangeRate.exchange(new MoneyToExchange(new BigDecimal("100.00"), Currency.EUR));

        //then
        assertTrue(calculate.equals(new Money(new BigDecimal("480.00"), Currency.PLN)));
    }
}