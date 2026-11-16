package co.edu.udea.certificacion.banking.models;

import java.util.Map;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String postCode;

    public Customer(String firstName, String lastName, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public static Customer from(Map<String, String> row) {
        return new Customer(row.get("first_name"), row.get("last_name"), row.get("post_code"));
    }

    /** Splits "Ana QAPrueba" into first name "Ana" and last name "QAPrueba". */
    public static Customer named(String fullName, String postCode) {
        String[] parts = fullName.trim().split(" ", 2);
        return new Customer(parts[0], parts.length > 1 ? parts[1] : "", postCode);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
