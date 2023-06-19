package pl.coztymit.exchange.identity.domain;

public class Surname {
    private String value;

    Surname() {
        // for JPA
    }

    public Surname(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be blank.");
        }
        this.value = value;
    }

    public boolean equals(Surname surname) {
        if (this == surname) return true;
        return value.equals(surname.value);
    }

    public boolean equals(String surname) {
        return value.equals(surname);
    }
}
