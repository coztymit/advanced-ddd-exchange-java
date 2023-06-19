package pl.coztymit.exchange.account.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
class TransactionTime {
    private LocalDateTime transactionTime;
    private TransactionTime() {
    }

    public TransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public boolean isItTheSameDay(LocalDateTime date){
        return this.transactionTime.isEqual(date);
    }


}
