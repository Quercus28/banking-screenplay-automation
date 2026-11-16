package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import co.edu.udea.certificacion.banking.questions.Dropdown;
import co.edu.udea.certificacion.banking.questions.Field;
import co.edu.udea.certificacion.banking.questions.TextOf;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.tasks.LoginAsCustomer;
import co.edu.udea.certificacion.banking.tasks.MakeTransaction;
import co.edu.udea.certificacion.banking.tasks.OpenBank;
import co.edu.udea.certificacion.banking.tasks.PrepareBalance;
import co.edu.udea.certificacion.banking.tasks.PrepareCustomerWithAccount;
import co.edu.udea.certificacion.banking.tasks.SelectAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.List;

import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.actor;
import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.verifyThat;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.ACCOUNTS;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.BALANCE;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.CURRENCY;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.INFORMATION;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.NUMBER;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.WELCOME;
import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage.CUSTOMER;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.AMOUNT;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.MESSAGE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

/** Customer steps: login, accounts, deposits and withdrawals. */
public class CustomerStepDefinitions {

    @Given("the customer {string} has logged in")
    public void theCustomerHasLoggedIn(String customer) {
        actor().wasAbleTo(OpenBank.withDemoData(), LoginAsCustomer.named(customer));
    }

    @When("the user logs in as the customer {string}")
    public void logInAsTheCustomer(String customer) {
        actor().attemptsTo(LoginAsCustomer.named(customer));
    }

    @When("the user opens the customer login without selecting a name")
    public void openTheCustomerLogin() {
        actor().attemptsTo(LoginAsCustomer.OpenCustomerLogin.withoutSelectingAName());
    }

    @When("the user selects the customer {string}")
    @When("the user goes back to the option {string}")
    public void selectTheCustomer(String option) {
        actor().attemptsTo(SelectFromOptions.byVisibleText(option).from(CUSTOMER));
    }

    @When("the user selects the account {string}")
    public void selectTheAccount(String number) {
        actor().attemptsTo(SelectAccount.number(number));
    }

    @When("the customer logs out")
    @Given("the customer has logged out")
    public void logOut() {
        actor().attemptsTo(PressButton.called("Logout"));
    }

    @Given("the customer {string} has an account in {string}")
    public void theCustomerHasAnAccount(String customer, String currency) {
        actor().wasAbleTo(PrepareCustomerWithAccount.in(customer, currency));
    }

    @Given("the account has a balance of {int}")
    public void theAccountHasABalanceOf(int balance) {
        actor().wasAbleTo(PrepareBalance.of(balance));
    }

    @Given("the customer has just deposited {int} successfully")
    public void theCustomerHasJustDeposited(int amount) {
        actor().wasAbleTo(MakeTransaction.deposit(String.valueOf(amount)));
        verifyThat(TextOf.the(MESSAGE), equalTo("Deposit Successful"));
    }

    @When("the customer deposits {word}")
    @When("the customer tries to type {string} as the deposit amount and submits it")
    public void deposit(String amount) {
        actor().attemptsTo(MakeTransaction.deposit(amount));
    }

    @When("the customer withdraws {word}")
    @When("the customer tries to type {string} as the withdrawal amount and submits it")
    public void withdraw(String amount) {
        actor().attemptsTo(MakeTransaction.withdrawal(amount));
    }

    @When("the customer submits the deposit without an amount")
    public void submitTheDepositWithoutAnAmount() {
        actor().attemptsTo(MakeTransaction.deposit(""));
    }

    @When("the customer submits the withdrawal without an amount")
    public void submitTheWithdrawalWithoutAnAmount() {
        actor().attemptsTo(MakeTransaction.withdrawal(""));
    }

    @Then("the welcome message {string} is displayed")
    public void theWelcomeMessageIsDisplayed(String message) {
        verifyThat(TextOf.the(WELCOME), equalTo(message));
    }

    @Then("the customer dropdown lists the preloaded customers")
    public void theDropdownListsThePreloadedCustomers(List<String> customers) {
        verifyThat(Dropdown.optionsOf(CUSTOMER), hasItems(customers.toArray(new String[0])));
    }

    @Then("the customer dropdown shows the option {string}")
    public void theCustomerDropdownShowsTheOption(String option) {
        verifyThat(Dropdown.selectedOptionIn(CUSTOMER), equalTo(option));
    }

    @Then("the account dropdown offers the accounts {string}, {string} and {string}")
    public void theAccountDropdownOffers(String first, String second, String third) {
        verifyThat(Dropdown.optionsOf(ACCOUNTS), hasItems(first, second, third));
    }

    @Then("the account {string} shows balance {int} and currency {string}")
    public void theAccountShowsBalanceAndCurrency(String number, int balance, String currency) {
        verifyThat(TextOf.the(NUMBER), equalTo(number));
        verifyThat(TextOf.the(BALANCE), equalTo(String.valueOf(balance)));
        verifyThat(TextOf.the(CURRENCY), equalTo(currency));
    }

    @Then("the selected account is {string}")
    public void theSelectedAccountIs(String number) {
        verifyThat(TextOf.the(NUMBER), equalTo(number));
    }

    @Then("the account balance is {int}")
    public void theAccountBalanceIs(int balance) {
        verifyThat(TextOf.the(BALANCE), equalTo(String.valueOf(balance)));
    }

    @Then("the account information is not displayed")
    public void theAccountInformationIsNotDisplayed() {
        verifyThat(Visibility.of(INFORMATION), is(false));
    }

    @Then("the user cannot make transactions without logging in again")
    public void cannotMakeTransactions() {
        verifyThat(Visibility.of(button("Deposit")), is(false));
        verifyThat(Visibility.of(button("Withdrawl")), is(false));
    }

    @Then("the amount field is empty")
    public void theAmountFieldIsEmpty() {
        verifyThat(Field.valueOf(AMOUNT), equalTo(""));
    }

    @Then("the amount field shows the required field validation")
    public void theAmountFieldIsRequired() {
        verifyThat(Field.isRequiredAndEmpty(AMOUNT), is(true));
    }

    @Then("the amount field shows the invalid value validation")
    public void theAmountFieldHasAnInvalidValue() {
        verifyThat(Field.hasAnInvalidValue(AMOUNT), is(true));
    }

    @Then("an invalid amount error message is displayed")
    public void anInvalidAmountErrorMessageIsDisplayed() {
        verifyThat(Question.about("whether a transaction error message is displayed").answeredBy(actor -> {
            String message = TextOf.the(MESSAGE).answeredBy(actor);
            return !message.isEmpty() && !message.toLowerCase().contains("successful");
        }), is(true));
    }
}
