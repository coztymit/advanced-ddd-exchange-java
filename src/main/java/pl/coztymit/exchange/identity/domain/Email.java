package pl.coztymit.exchange.identity.domain;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private String value;

    Email() {
    }

    public Email(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email: " + value);
        }
        this.value = value;
    }

    public boolean equals(Email email) {
        if (this == email) return true;
        return value.equals(email.value);
    }

    private boolean isValid(String value) {
        return value != null && !value.trim().isEmpty() && EMAIL_PATTERN.matcher(value).matches();
    }

    public boolean equals(String email) {
        return value.equals(email);
    }
}
