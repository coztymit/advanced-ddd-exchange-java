package pl.coztymit.exchange.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Random;

@Embeddable
public class TraderNumber {
    @JsonProperty
    private String value;

    TraderNumber() {
    }

    public TraderNumber(String number) {
        if (!isValidTraderNumber(number)) {
            throw new RuntimeException("Incorrect trader number");
        }
        this.value = number;
    }


    public static TraderNumber generateNewNumber() {
        Random random = new Random();
        String letters = random.ints(3, 'A', 'Z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        LocalDate currentDate = LocalDate.now();
        String day = String.format("%02d", currentDate.getDayOfMonth());
        String year = String.valueOf(currentDate.getYear());

        int number = random.nextInt(1000);  // generate three digits number
        String digits = String.format("%03d", number);  // leading zeroes if number < 100

        String traderNumber = letters + "-" + day + "-" + year + "-" + digits;

        return new TraderNumber(traderNumber);
    }

    public static boolean isValidTraderNumber(String traderNumber) {
        if (traderNumber == null || traderNumber.length() != 15) {
            return false;
        }

        // The pattern is: 3 uppercase letters, hyphen, 2 digits day, hyphen, 4 digits year, hyphen, 3 digits
        String pattern = "[A-Z]{3}-\\d{2}-\\d{4}-\\d{3}";

        return traderNumber.matches(pattern);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
