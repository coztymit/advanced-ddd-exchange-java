package pl.coztymit.exchange.account.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class FundsTest {

    @Test
    void shouldReturnTrueWhenValueIsGreater() {
        Funds funds1 = new Funds(new BigDecimal("10"));
        Funds funds2 = new Funds(new BigDecimal("5"));

        assertTrue(funds1.greaterOrEqualsThan(funds2));
    }

    @Test
    void shouldReturnTrueWhenValueIsEqual() {
        Funds funds1 = new Funds(new BigDecimal("10"));
        Funds funds2 = new Funds(new BigDecimal("10"));

        assertTrue(funds1.greaterOrEqualsThan(funds2));
    }

    @Test
    void shouldReturnFalseWhenValueIsLess() {
        Funds funds1 = new Funds(new BigDecimal("5"));
        Funds funds2 = new Funds(new BigDecimal("10"));

        assertFalse(funds1.greaterOrEqualsThan(funds2));
    }
}