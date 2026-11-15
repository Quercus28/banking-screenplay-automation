package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.models.Movimiento;
import co.edu.udea.certificacion.banking.userinterfaces.PaginaMovimientos;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/** Filas de la tabla Transactions en el orden mostrado. */
public final class HistorialDeMovimientos {

    private HistorialDeMovimientos() {
    }

    public static Question<List<Movimiento>> movimientos() {
        return Question.about("los movimientos del historial").answeredBy(actor ->
                PaginaMovimientos.FILAS.resolveAllFor(actor).stream()
                        .filter(WebElementFacade::isCurrentlyVisible)
                        .map(fila -> {
                            List<WebElement> celdas = fila.findElements(By.tagName("td"));
                            return new Movimiento(celdas.get(0).getText().trim(),
                                    celdas.get(1).getText().trim(), celdas.get(2).getText().trim());
                        })
                        .collect(Collectors.toList()));
    }

    public static Question<Integer> cantidad() {
        return Question.about("la cantidad de movimientos").answeredBy(actor -> movimientos().answeredBy(actor).size());
    }

    public static Question<List<LocalDateTime>> fechas() {
        return Question.about("las fechas de los movimientos").answeredBy(actor ->
                movimientos().answeredBy(actor).stream().map(Movimiento::getFechaHora).collect(Collectors.toList()));
    }
}
