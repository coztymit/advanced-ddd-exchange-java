package pl.coztymit.exchange.quoting.domain;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuoteDomainServiceTest {
    @Mock
    private ExchangeRateAdvisor advisor1;
    @Mock
    private ExchangeRateAdvisor advisor2;
    @Mock
    private ExchangeRateAdvisor advisor3;

    private Currency PLN = new Currency("PLN");
    private Currency EUR = new Currency("EUR");

    @Test
    public void shouldReturnBestExchangeRateFromAllAdvisors() {
        // given
        ExchangeRate rate1 = new ExchangeRate(PLN, EUR, new BigDecimal("4.20"));
        ExchangeRate rate2 = new ExchangeRate(PLN, EUR, new BigDecimal("4.19"));
        ExchangeRate rate3 = new ExchangeRate(PLN, EUR, new BigDecimal("4.50"));

        when(advisor1.exchangeRate(PLN, EUR)).thenReturn(Optional.of(rate1));
        when(advisor2.exchangeRate(PLN, EUR)).thenReturn(Optional.of(rate2));
        when(advisor3.exchangeRate(PLN, EUR)).thenReturn(Optional.of(rate3));

        // when
        ExchangeRate bestRate = new ExchangeDomainService().getBestExchangeRate(Arrays.asList(advisor1, advisor2, advisor3), PLN, EUR);

        // then
        assertEquals(rate3, bestRate);
    }


}