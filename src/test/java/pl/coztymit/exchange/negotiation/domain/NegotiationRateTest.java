package pl.coztymit.exchange.negotiation.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NegotiationRateTest {

    @Test
    void shouldCalculateDifferenceInPercentage() {
        //given
        var proposedRate = new BigDecimal("3");
        var baseExchangeRate = new BigDecimal("4");
        var expectedDifferenceInPercentage = new BigDecimal("25.00");

        //when
        var negotiationRate = new NegotiationRate(proposedRate, baseExchangeRate);

        //then
        assertEquals(expectedDifferenceInPercentage, negotiationRate.differenceInPercentage());
    }

}