package atm.model;

import atm.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount, LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
