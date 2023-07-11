package pl.coztymit.exchange.quoting.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class ExpirationDate {
    private LocalDateTime expirationDate;

    private ExpirationDate() {
    }

    private ExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static ExpirationDate oneDayExpirationDate(){
        return new ExpirationDate(LocalDateTime.now().plusDays(1));
    }
    public static ExpirationDate oneHourExpirationDate(){
        return new ExpirationDate(LocalDateTime.now().plusHours(1));
    }
}
