package co.edu.udea.certificacion.banking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/** Row of an account's transaction history. */
public class Transaction {

    private static final DateTimeFormatter MEDIUM_FORMAT =
            DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a", Locale.US);

    private final String date;
    private final String amount;
    private final String type;

    public Transaction(String date, String amount, String type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public static Transaction of(String amount, String type) {
        return new Transaction("", amount, type);
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(date, MEDIUM_FORMAT);
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    /** Two transactions are equal when amount and type match; the date depends on when the test runs. */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) other;
        return amount.equals(transaction.amount) && type.equals(transaction.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, type);
    }

    @Override
    public String toString() {
        return amount + " " + type;
    }
}
