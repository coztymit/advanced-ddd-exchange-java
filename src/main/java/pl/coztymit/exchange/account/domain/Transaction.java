package pl.coztymit.exchange.account.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Encja wewnętrzna agreagatu
@Entity
@Table(name = "transactions")
class Transaction {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "transaction_number"))
    private TransactionNumber number;

    @AttributeOverride(name = "type", column = @Column(name = "transaction_type"))
    private TransactionType type;

    @AttributeOverrides({
            @AttributeOverride(name = "value.value", column = @Column(name = "fund_value")),
            @AttributeOverride(name = "value.currency.value", column = @Column(name = "fund_currency"))
    })
    private Funds value;


    @AttributeOverride(name = "transactionTime", column = @Column(name = "transaction_date"))
    private TransactionTime date;

    private Transaction() {
    }
    Transaction (TransactionType type, Funds value) {
        this.number = TransactionNumber.generate();
        this.type = type;
        this.value = value;
        this.date = new TransactionTime(LocalDateTime.now());
    }

    public TransactionTime transactionDate() {
        return date;
    }

    public TransactionType type() {
        return type;
    }
}
