package pl.coztymit.exchange.identity.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void shouldCreateEmailWhenValueIsValid() {
        String validEmail = "test@example.com";
        assertDoesNotThrow(() -> new Email(validEmail));
    }

    @Test
    public void shouldThrowExceptionWhenValueIsInvalid() {
        String invalidEmail = "invalid email";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void shouldThrowExceptionWhenValueIsEmpty() {
        String emptyEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(emptyEmail));
    }

    @Test
    public void shouldThrowExceptionWhenValueIsNull() {
        String nullEmail = null;
        assertThrows(IllegalArgumentException.class, () -> new Email(nullEmail));
    }

}