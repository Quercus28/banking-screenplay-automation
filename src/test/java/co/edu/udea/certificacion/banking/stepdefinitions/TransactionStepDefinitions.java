package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.interactions.WaitForNextMinute;
import co.edu.udea.certificacion.banking.models.Transaction;
import co.edu.udea.certificacion.banking.questions.Navigation;
import co.edu.udea.certificacion.banking.questions.TransactionHistory;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.tasks.FilterTransactions;
import co.edu.udea.certificacion.banking.tasks.MakeTransaction;
import co.edu.udea.certificacion.banking.tasks.SortList;
import co.edu.udea.certificacion.banking.tasks.ViewTransactions;
import co.edu.udea.certificacion.banking.utils.SortOrder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Question;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.actor;
import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.verifyThat;
import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.ERROR_MESSAGES;
import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage.END_DATE;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage.START_DATE;
import static co.edu.udea.certificacion.banking.utils.Constants.LAST_WITHDRAWAL_TIME;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/** Transaction history steps (Transactions). */
public class TransactionStepDefinitions {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Given("the customer has made the transactions")
    public void theCustomerHasMadeTheTransactions(List<Map<String, String>> transactions) throws InterruptedException {
        for (Map<String, String> transaction : transactions) {
            String amount = transaction.get("amount");
            actor().wasAbleTo("withdrawal".equals(transaction.get("type"))
                    ? MakeTransaction.withdrawal(amount)
                    : MakeTransaction.deposit(amount));
            // If every transaction falls in the same second, the start date filter ends up above its maximum
            // (truncated to seconds) and the table is emptied. They are spaced out the way a user would.
            Thread.sleep(1100);
        }
    }

    @Given("one minute later the customer made a withdrawal of {int}")
    public void oneMinuteLaterTheCustomerMadeAWithdrawal(int amount) throws InterruptedException {
        actor().wasAbleTo(WaitForNextMinute.toStart(), MakeTransaction.withdrawal(String.valueOf(amount)));
        actor().remember(LAST_WITHDRAWAL_TIME, LocalDateTime.now());
        // The filter's upper limit is the time the view is opened, with second precision:
        // one second is allowed to pass so the withdrawal falls within that limit.
        Thread.sleep(1500);
    }

    @When("the customer views the account transactions")
    public void viewTheTransactions() {
        actor().attemptsTo(ViewTransactions.ofTheAccount());
    }

    @When("the customer filters the transactions from {string} to {string}")
    public void filterTheTransactions(String from, String to) {
        actor().attemptsTo(FilterTransactions.between(from, to));
    }

    @When("the customer filters the transactions from the minute of the withdrawal to now")
    public void filterFromTheMinuteOfTheWithdrawal() {
        LocalDateTime withdrawal = actor().recall(LAST_WITHDRAWAL_TIME);
        String from = withdrawal.truncatedTo(ChronoUnit.MINUTES).format(DATE_TIME);
        String to = END_DATE.resolveFor(actor()).getAttribute("max");
        actor().attemptsTo(FilterTransactions.between(from, to));
    }

    @When("the customer sets the filter end date to {int} minutes in the future")
    public void setTheEndDateInTheFuture(int minutes) {
        actor().attemptsTo(FilterTransactions.until(LocalDateTime.now().plusMinutes(minutes).format(DATE_TIME)));
    }

    @When("the customer sorts the transactions by {string}")
    public void sortTheTransactions(String column) {
        actor().attemptsTo(SortList.byColumn(column));
    }

    @Then("the history shows the transactions")
    public void theHistoryShowsTheTransactions(List<Map<String, String>> expected) {
        List<Transaction> transactions = expected.stream()
                .map(row -> Transaction.of(row.get("amount"), row.get("type")))
                .collect(Collectors.toList());
        verifyThat(TransactionHistory.transactions(), contains(transactions.toArray(new Transaction[0])));
    }

    @Then("the history shows {int} transactions")
    public void theHistoryShowsTheCount(int count) {
        verifyThat(TransactionHistory.count(), equalTo(count));
    }

    @Then("the history shows no transactions")
    public void theHistoryShowsNoTransactions() {
        verifyThat(TransactionHistory.count(), equalTo(0));
    }

    @Then("the date filters and the {string} and {string} buttons are displayed")
    public void theFiltersAndButtonsAreDisplayed(String first, String second) {
        verifyThat(Visibility.of(START_DATE), is(true));
        verifyThat(Visibility.of(END_DATE), is(true));
        verifyThat(Visibility.of(button(first)), is(true));
        verifyThat(Visibility.of(button(second)), is(true));
    }

    @Then("the date filters and the {string} button are not displayed")
    public void theFiltersAndButtonAreNotDisplayed(String text) {
        verifyThat(Visibility.of(START_DATE), is(false));
        verifyThat(Visibility.of(END_DATE), is(false));
        verifyThat(Visibility.of(button(text)), is(false));
    }

    @Then("the user is told that the date range is not allowed")
    public void theUserIsToldTheRangeIsNotAllowed() {
        verifyThat(Question.about("whether a message about the date range is displayed").answeredBy(actor ->
                ERROR_MESSAGES.resolveAllFor(actor).stream()
                        .anyMatch(element -> element.isCurrentlyVisible() && !element.getText().trim().isEmpty())),
                is(true));
    }

    @Then("the transactions are sorted by date in {word} order")
    public void theTransactionsAreSorted(String direction) {
        verifyThat(TransactionHistory.dates(),
                "descending".equals(direction) ? SortOrder.<LocalDateTime>descending() : SortOrder.<LocalDateTime>ascending());
    }

    @Then("the table goes back to the top of the history")
    public void theTableGoesBackToTheTop() {
        verifyThat(Navigation.currentRoute(), endsWith("#anchor"));
    }
}
