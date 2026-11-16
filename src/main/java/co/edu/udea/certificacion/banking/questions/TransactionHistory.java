package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.models.Transaction;
import co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/** Rows of the Transactions table in the order they are displayed. */
public final class TransactionHistory {

    private TransactionHistory() {
    }

    public static Question<List<Transaction>> transactions() {
        return Question.about("the transactions in the history").answeredBy(actor ->
                TransactionsPage.ROWS.resolveAllFor(actor).stream()
                        .filter(WebElementFacade::isCurrentlyVisible)
                        .map(row -> {
                            List<WebElement> cells = row.findElements(By.tagName("td"));
                            return new Transaction(cells.get(0).getText().trim(),
                                    cells.get(1).getText().trim(), cells.get(2).getText().trim());
                        })
                        .collect(Collectors.toList()));
    }

    public static Question<Integer> count() {
        return Question.about("the number of transactions").answeredBy(actor -> transactions().answeredBy(actor).size());
    }

    public static Question<List<LocalDateTime>> dates() {
        return Question.about("the dates of the transactions").answeredBy(actor ->
                transactions().answeredBy(actor).stream().map(Transaction::getDateTime).collect(Collectors.toList()));
    }
}
