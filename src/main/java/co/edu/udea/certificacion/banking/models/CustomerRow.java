package co.edu.udea.certificacion.banking.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Row of the manager's customer list. */
public class CustomerRow {

    private final String firstName;
    private final String lastName;
    private final String postCode;
    private final String accounts;

    public CustomerRow(String firstName, String lastName, String postCode, String accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.accounts = accounts;
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

    public String getAccounts() {
        return accounts;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public List<String> getAccountList() {
        return accounts.isEmpty() ? Collections.emptyList() : Arrays.asList(accounts.split("\s+"));
    }

    public boolean is(String firstName, String lastName) {
        return this.firstName.equals(firstName) && this.lastName.equals(lastName);
    }

    @Override
    public String toString() {
        return firstName + " | " + lastName + " | " + postCode + " | " + accounts;
    }
}
