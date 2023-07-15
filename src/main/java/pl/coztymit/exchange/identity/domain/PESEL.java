package pl.coztymit.exchange.identity.domain;

public class PESEL {
    private String value;

    PESEL() {
    }

    public PESEL(String value) {
        if (!isValidPesel(value)) {
            throw new IllegalArgumentException("Invalid PESEL.");
        }
        this.value = value;
    }

    public boolean equals(PESEL pesel) {
        if (this == pesel) return true;
        return value.equals(pesel.value);
    }

    public boolean equals(String pesel) {
        return value.equals(pesel);
    }

    private boolean isValidPesel(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * Character.getNumericValue(pesel.charAt(i));
        }

        int checkSum = (10 - (sum % 10)) % 10;

        return checkSum == Character.getNumericValue(pesel.charAt(10));
    }

    @Override
    public String toString() {
        return value;
    }
}
