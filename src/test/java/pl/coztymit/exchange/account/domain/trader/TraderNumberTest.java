package pl.coztymit.exchange.account.domain.trader;

import org.junit.jupiter.api.Test;
import pl.coztymit.exchange.account.domain.TraderNumber;

import static org.junit.jupiter.api.Assertions.*;

class TraderNumberTest {
    @Test
    public void testIsValidTraderNumber() {
        assertTrue(TraderNumber.isValidTraderNumber("AAA-12-2023-456"));
        assertFalse(TraderNumber.isValidTraderNumber("AAA-13-2023-4567"));  // incorrect day
        assertFalse(TraderNumber.isValidTraderNumber("AA-12-2023-456"));    // not enough letters
        assertFalse(TraderNumber.isValidTraderNumber("AAA-12-23-456"));     // incorrect year
        assertFalse(TraderNumber.isValidTraderNumber("aaa-12-2023-456"));   // lower case letters
    }

    @Test
    public void testGenerateNewNumber() {
        TraderNumber generatedNumber = TraderNumber.generateNewNumber();
        assertNotNull(generatedNumber);
        assertTrue(TraderNumber.isValidTraderNumber(generatedNumber.toString()));
    }

    @Test
    public void testConstructorThrowsExceptionWithInvalidNumber() {
        assertThrows(RuntimeException.class, () -> new TraderNumber("invalid number"));
    }

    @Test
    public void testConstructorCreatesTraderNumberWithValidNumber() {
        assertDoesNotThrow(() -> new TraderNumber("AAA-12-2023-456"));
    }

}