package pl.coztymit.exchange.identity.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PESELTest {
    @Test
    public void shouldCreatePeselWhenValid() {
        // given
        String validPesel = "55072993619";

        // when
        PESEL pesel = new PESEL(validPesel);

        // then
        assertTrue(pesel.equals(validPesel));
    }

    @Test
    public void shouldThrowExceptionWhenPeselInvalid() {
        // given
        String invalidPesel = "82080503628";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PESEL(invalidPesel));
    }
}