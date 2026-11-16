package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.models.CustomerRow;
import co.edu.udea.certificacion.banking.userinterfaces.CustomersPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/** Content of the manager's Customers table. */
public final class CustomerList {

    private CustomerList() {
    }

    public static Question<List<CustomerRow>> rows() {
        return Question.about("the rows of the customer list").answeredBy(actor ->
                CustomersPage.ROWS.resolveAllFor(actor).stream()
                        .filter(WebElementFacade::isCurrentlyVisible)
                        .map(CustomerList::toRow)
                        .collect(Collectors.toList()));
    }

    public static Question<Integer> count() {
        return Question.about("the number of listed customers").answeredBy(actor -> rows().answeredBy(actor).size());
    }

    public static Question<List<String>> fullNames() {
        return Question.about("the names of the listed customers").answeredBy(actor ->
                rows().answeredBy(actor).stream().map(CustomerRow::getFullName).collect(Collectors.toList()));
    }

    public static Question<List<String>> columns() {
        return Question.about("the columns of the customer list").answeredBy(actor ->
                CustomersPage.COLUMNS.resolveAllFor(actor).stream()
                        .map(column -> column.getText().trim())
                        .collect(Collectors.toList()));
    }

    /** Values of a column (First Name, Last Name or Post Code) in the order they are displayed. */
    public static Question<List<String>> columnValues(String column) {
        return Question.about("the values of the " + column + " column").answeredBy(actor ->
                rows().answeredBy(actor).stream().map(row -> {
                    switch (column) {
                        case "First Name":
                            return row.getFirstName();
                        case "Last Name":
                            return row.getLastName();
                        case "Post Code":
                            return row.getPostCode();
                        default:
                            throw new IllegalArgumentException("Column cannot be sorted: " + column);
                    }
                }).collect(Collectors.toList()));
    }

    public static Question<Boolean> everyRowHasTheButton(String text) {
        return Question.about("whether every customer has the " + text + " button").answeredBy(actor -> {
            List<WebElementFacade> rows = CustomersPage.ROWS.resolveAllFor(actor);
            return !rows.isEmpty() && rows.stream().allMatch(row ->
                    row.findElements(By.xpath(".//button")).stream()
                            .anyMatch(button -> button.getText().trim().equals(text)));
        });
    }

    private static CustomerRow toRow(WebElementFacade row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        return new CustomerRow(text(cells, 0), text(cells, 1), text(cells, 2), text(cells, 3));
    }

    private static String text(List<WebElement> cells, int index) {
        return cells.size() > index ? cells.get(index).getText().trim() : "";
    }
}
