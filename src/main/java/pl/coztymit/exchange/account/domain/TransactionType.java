package pl.coztymit.exchange.account.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TransactionType {
    public static TransactionType CARD = new TransactionType("CARD");
    public static TransactionType CURRENCY_EXCHANGE = new TransactionType("CURRENCY_EXCHANGE");
    public static TransactionType TRANSFER_BETWEEN_ACCOUNTS_DEPOSIT = new TransactionType("TRANSFER_BETWEEN_ACCOUNTS_DEPOSIT");
    public static TransactionType TRANSFER_BETWEEN_ACCOUNTS_WITHDRAW = new TransactionType("TRANSFER_BETWEEN_ACCOUNTS_WITHDRAW");
    public static TransactionType WITHDRAW = new TransactionType("WITHDRAW");
    public static TransactionType DEPOSIT = new TransactionType("DEPOSIT");

    private String type;

    private TransactionType(String type) {
        this.type = type;
    }
    private TransactionType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
