package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.interactions.ResetBankData;
import co.edu.udea.certificacion.banking.models.Customer;
import co.edu.udea.certificacion.banking.models.CustomerRow;
import co.edu.udea.certificacion.banking.questions.CustomerList;
import co.edu.udea.certificacion.banking.questions.Dropdown;
import co.edu.udea.certificacion.banking.questions.Field;
import co.edu.udea.certificacion.banking.questions.LastAlert;
import co.edu.udea.certificacion.banking.tasks.DeleteCustomer;
import co.edu.udea.certificacion.banking.tasks.GoToManagerTab;
import co.edu.udea.certificacion.banking.tasks.LoginAsCustomer;
import co.edu.udea.certificacion.banking.tasks.OpenAccount;
import co.edu.udea.certificacion.banking.tasks.OpenBank;
import co.edu.udea.certificacion.banking.tasks.RegisterCustomer;
import co.edu.udea.certificacion.banking.tasks.SearchCustomer;
import co.edu.udea.certificacion.banking.tasks.SortList;
import co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm;
import co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage;
import co.edu.udea.certificacion.banking.userinterfaces.CustomersPage;
import co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm;
import co.edu.udea.certificacion.banking.utils.Memory;
import co.edu.udea.certificacion.banking.utils.SortOrder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.actor;
import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.verifyThat;
import static co.edu.udea.certificacion.banking.utils.Constants.ACCOUNT_CREATED_PREFIX;
import static co.edu.udea.certificacion.banking.utils.Constants.ACCOUNT_NUMBERS;
import static co.edu.udea.certificacion.banking.utils.Constants.CUSTOMER_CREATED_PREFIX;
import static co.edu.udea.certificacion.banking.utils.Constants.CUSTOMER_IDS;
import static co.edu.udea.certificacion.banking.utils.Constants.PRELOADED_CUSTOMERS;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

/** Manager steps: customer registration, account opening and customer list management. */
public class ManagerStepDefinitions {

    @Given("the manager is on the {string} tab")
    public void theManagerIsOnTheTab(String tab) {
        actor().wasAbleTo(OpenBank.withDemoData(), GoToManagerTab.called(tab));
    }

    @Given("the manager goes to the {string} tab")
    public void theManagerGoesToTheTab(String tab) {
        actor().wasAbleTo(GoToManagerTab.called(tab));
    }

    @Given("the manager has registered the customers")
    public void theManagerHasRegisteredTheCustomers(List<Map<String, String>> customers) {
        actor().wasAbleTo(OpenBank.withDemoData());
        customers.forEach(row -> actor().wasAbleTo(RegisterCustomer.with(Customer.from(row))));
    }

    @Given("the manager has registered a customer with first name {string}, last name {string} and post code {string}")
    @When("the manager registers a customer with first name {string}, last name {string} and post code {string}")
    public void registerACustomer(String firstName, String lastName, String postCode) {
        actor().attemptsTo(RegisterCustomer.with(firstName, lastName, postCode));
    }

    @When("the manager submits the registration form without filling in any field")
    public void submitTheEmptyForm() {
        actor().attemptsTo(RegisterCustomer.with("", "", ""));
    }

    @Given("the manager has opened an account in {string} for the customer {string}")
    @When("the manager opens an account in {string} for the customer {string}")
    public void openAnAccount(String currency, String customer) {
        actor().attemptsTo(OpenAccount.in(currency, customer));
    }

    @When("the manager processes an account in {string} without selecting a customer")
    public void openAccountWithoutCustomer(String currency) {
        actor().attemptsTo(OpenAccount.in(currency, ""));
    }

    @When("the manager processes an account for the customer {string} without selecting a currency")
    public void openAccountWithoutCurrency(String customer) {
        actor().attemptsTo(OpenAccount.in("", customer));
    }

    @When("the manager opens the application in a new browser session")
    public void openInANewSessionAsManager() {
        actor().attemptsTo(
                ResetBankData.andOpen("#/manager/list"),
                WaitUntil.the(CustomersPage.SEARCH, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    @When("the manager searches for {string} in the customer list")
    public void searchInTheList(String text) {
        actor().attemptsTo(SearchCustomer.with(text));
    }

    @When("the manager sorts the list by the {string} column")
    public void sortTheList(String column) {
        actor().attemptsTo(SortList.byColumn(column));
    }

    @When("the manager deletes the customer {string}")
    public void deleteTheCustomer(String customer) {
        actor().attemptsTo(DeleteCustomer.called(customer));
    }

    @Then("the alert {string} is displayed")
    public void theAlertIsDisplayed(String text) {
        verifyThat(LastAlert.text(), equalTo(text));
    }

    @Then("the alert {string} is not displayed")
    public void theAlertIsNotDisplayed(String text) {
        verifyThat(LastAlert.text(), not(equalTo(text)));
    }

    @Then("no alert is displayed")
    public void noAlertIsDisplayed() {
        verifyThat(LastAlert.text(), equalTo(""));
    }

    @Then("the alert {string} is displayed followed by the assigned id")
    public void theAlertIsDisplayedWithTheId(String prefix) {
        List<Integer> ids = Memory.list(actor(), CUSTOMER_IDS);
        verifyThat(LastAlert.text(), startsWith(prefix));
        verifyThat(LastAlert.text(), equalTo(CUSTOMER_CREATED_PREFIX + last(ids)));
    }

    @Then("the alert {string} is displayed followed by the assigned account number")
    public void theAlertIsDisplayedWithTheAccountNumber(String prefix) {
        List<String> accounts = Memory.list(actor(), ACCOUNT_NUMBERS);
        verifyThat(LastAlert.text(), startsWith(prefix));
        verifyThat(LastAlert.text(), equalTo(ACCOUNT_CREATED_PREFIX + last(accounts)));
    }

    @Then("the assigned id follows the previous customer's id")
    public void theIdIsConsecutive() {
        verifyThat(consecutive(CUSTOMER_IDS), is(true));
    }

    @Then("the assigned account number follows the previous account number")
    public void theAccountNumberIsConsecutive() {
        verifyThat(consecutive(ACCOUNT_NUMBERS), is(true));
    }

    @Then("the registration form is cleared")
    public void theFormIsCleared() {
        verifyThat(Field.valueOf(AddCustomerForm.FIRST_NAME), equalTo(""));
        verifyThat(Field.valueOf(AddCustomerForm.LAST_NAME), equalTo(""));
        verifyThat(Field.valueOf(AddCustomerForm.POST_CODE), equalTo(""));
    }

    @Then("the field {string} shows the required field validation")
    public void theFieldIsRequired(String field) {
        verifyThat(Field.isRequiredAndEmpty(AddCustomerForm.field(field)), is(true));
    }

    @Then("the field {string} shows the required selection validation")
    public void theDropdownIsRequired(String field) {
        verifyThat(Field.isRequiredAndEmpty(OpenAccountForm.field(field)), is(true));
    }

    @Then("no new customer is registered")
    public void noNewCustomerIsRegistered() {
        int registered = Memory.list(actor(), CUSTOMER_IDS).size();
        actor().attemptsTo(SearchCustomer.withoutFilter());
        verifyThat(CustomerList.count(), equalTo(PRELOADED_CUSTOMERS + registered));
    }

    @Then("no new account is created")
    public void noNewAccountIsCreated() {
        verifyThat(LastAlert.text(), not(startsWith(ACCOUNT_CREATED_PREFIX)));
    }

    @Then("the system rejects the registration because of an invalid name format")
    public void theSystemRejectsTheNameFormat() {
        verifyThat(LastAlert.text(), not(startsWith(CUSTOMER_CREATED_PREFIX)));
    }

    @Then("the system reports that the fields are required or invalid")
    public void theSystemReportsInvalidFields() {
        verifyThat(Question.about("whether the fields are reported as required or invalid").answeredBy(actor -> {
            String alert = LastAlert.text().answeredBy(actor).toLowerCase();
            boolean fieldsAlert = alert.contains("required") || alert.contains("invalid");
            boolean nativeValidation = Field.isRequiredAndEmpty(AddCustomerForm.FIRST_NAME).answeredBy(actor)
                    || Field.isRequiredAndEmpty(AddCustomerForm.LAST_NAME).answeredBy(actor)
                    || Field.isRequiredAndEmpty(AddCustomerForm.POST_CODE).answeredBy(actor);
            return fieldsAlert || nativeValidation;
        }), is(true));
    }

    @Then("the customer {string} {string} with post code {string} appears in the list without an account number")
    public void theCustomerAppearsWithoutAccount(String firstName, String lastName, String postCode) {
        actor().attemptsTo(SearchCustomer.with(lastName));
        verifyThat(rowOf(firstName, lastName), equalTo(Optional.of(firstName + " | " + lastName + " | " + postCode + " | ")));
    }

    @Then("searching for {string} in the customer list returns {int} customer(s)")
    public void theSearchReturns(String text, int count) {
        actor().attemptsTo(SearchCustomer.with(text));
        verifyThat(CustomerList.count(), equalTo(count));
    }

    @Then("the list shows {int} customer(s)")
    public void theListShowsTheCount(int count) {
        verifyThat(CustomerList.count(), equalTo(count));
    }

    @Then("the customer list has the columns {string}, {string}, {string}, {string} and {string}")
    public void theListHasTheColumns(String c1, String c2, String c3, String c4, String c5) {
        verifyThat(CustomerList.columns(), contains(c1, c2, c3, c4, c5));
    }

    @Then("every customer has a {string} button")
    public void everyCustomerHasAButton(String text) {
        verifyThat(CustomerList.everyRowHasTheButton(text), is(true));
    }

    @Then("the list only shows the customer {string} {string} with post code {string} and accounts {string}")
    public void theListOnlyShowsTheRow(String firstName, String lastName, String postCode, String accounts) {
        verifyThat(Question.about("the rows of the list").answeredBy(
                        actor -> CustomerList.rows().answeredBy(actor).toString()),
                equalTo("[" + firstName + " | " + lastName + " | " + postCode + " | " + accounts + "]"));
    }

    @Then("the list shows the customer {string} {string} with post code {string}")
    public void theListShowsTheCustomer(String firstName, String lastName, String postCode) {
        verifyThat(Question.about("the customer's post code").answeredBy(actor ->
                        CustomerList.rows().answeredBy(actor).stream()
                                .filter(row -> row.is(firstName, lastName))
                                .map(CustomerRow::getPostCode)
                                .findFirst()),
                equalTo(Optional.of(postCode)));
    }

    @Then("the list shows the customers")
    public void theListShowsTheCustomers(List<String> customers) {
        verifyThat(CustomerList.fullNames(), containsInAnyOrder(customers.toArray(new String[0])));
    }

    @Then("the list only shows the customer {string}")
    public void theListOnlyShowsTheCustomer(String customer) {
        verifyThat(CustomerList.fullNames(), contains(customer));
    }

    @Then("the list is sorted by {string} in {word} order")
    public void theListIsSorted(String column, String direction) {
        verifyThat(CustomerList.columnValues(column),
                "descending".equals(direction) ? SortOrder.textsDescending() : SortOrder.textsAscending());
    }

    @Then("the customer {string} has {int} accounts in the customer list")
    public void theCustomerHasAccounts(String customer, int count) {
        actor().attemptsTo(SearchCustomer.withoutFilter());
        verifyThat(accountCountOf(customer), equalTo(count));
    }

    @Then("each account appears next to its customer in the customer list")
    public void eachAccountAppearsNextToItsCustomer(List<Map<String, String>> expected) {
        actor().attemptsTo(SearchCustomer.withoutFilter());
        Map<String, List<String>> opened = Memory.accountsByCustomer(actor());
        for (Map<String, String> row : expected) {
            String customer = row.get("customer");
            verifyThat(accountCountOf(customer), equalTo(Integer.valueOf(row.get("accounts"))));
            verifyThat(accountNumbersOf(customer), hasItems(opened.get(customer).toArray(new String[0])));
        }
    }

    @Then("the currency dropdown offers the options {string}, {string} and {string}")
    public void theCurrencyDropdownOffers(String first, String second, String third) {
        verifyThat(Dropdown.optionsOf(OpenAccountForm.CURRENCY), hasItems(first, second, third));
    }

    @Then("the customer and currency dropdowns go back to their empty option")
    public void theDropdownsGoBackToTheirEmptyOption() {
        verifyThat(Dropdown.selectedOptionIn(OpenAccountForm.CUSTOMER), equalTo(OpenAccountForm.NO_CUSTOMER));
        verifyThat(Dropdown.selectedOptionIn(OpenAccountForm.CURRENCY), equalTo(OpenAccountForm.NO_CURRENCY));
    }

    @Then("the customer {string} does not appear in the customer dropdown of {string}")
    public void theCustomerDoesNotAppearInTheDropdown(String customer, String view) {
        if ("Open Account".equals(view)) {
            actor().attemptsTo(GoToManagerTab.called("Open Account"));
            verifyThat(Dropdown.optionsOf(OpenAccountForm.CUSTOMER), not(hasItems(customer)));
        } else {
            actor().attemptsTo(LoginAsCustomer.OpenCustomerLogin.withoutSelectingAName());
            verifyThat(Dropdown.optionsOf(CustomerLoginPage.CUSTOMER), not(hasItems(customer)));
        }
    }

    private static Question<Optional<String>> rowOf(String firstName, String lastName) {
        return Question.about("the row of " + firstName + " " + lastName).answeredBy(actor ->
                CustomerList.rows().answeredBy(actor).stream()
                        .filter(row -> row.is(firstName, lastName))
                        .map(CustomerRow::toString)
                        .findFirst());
    }

    private static Question<List<String>> accountNumbersOf(String customer) {
        return Question.about("the accounts of " + customer).answeredBy(actor ->
                CustomerList.rows().answeredBy(actor).stream()
                        .filter(row -> row.getFullName().equals(customer))
                        .map(CustomerRow::getAccountList)
                        .findFirst()
                        .orElse(List.of()));
    }

    private static Question<Integer> accountCountOf(String customer) {
        return Question.about("the number of accounts of " + customer)
                .answeredBy(actor -> accountNumbersOf(customer).answeredBy(actor).size());
    }

    private static Question<Boolean> consecutive(String key) {
        return Question.about("whether the last two values are consecutive").answeredBy(actor -> {
            List<Object> values = Memory.list(actor, key);
            if (values.size() < 2) {
                return false;
            }
            long previous = Long.parseLong(values.get(values.size() - 2).toString());
            long current = Long.parseLong(values.get(values.size() - 1).toString());
            return current == previous + 1;
        });
    }

    private static <T> String last(List<T> values) {
        return values.isEmpty() ? "(none)" : values.get(values.size() - 1).toString();
    }
}
