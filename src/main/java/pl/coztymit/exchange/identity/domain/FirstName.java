package pl.coztymit.exchange.identity.domain;

import jakarta.persistence.Embeddable;

public class FirstName {
    private String value;

    FirstName() {
        // for JPA
    }

    public FirstName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank.");
        }
        this.value = value;
    }

    public boolean equals(FirstName firstName) {
        if (this == firstName) return true;
        return value.equals(firstName.value);
    }

    public boolean equals(String firstName) {
        return value.equals(firstName);
    }
}
