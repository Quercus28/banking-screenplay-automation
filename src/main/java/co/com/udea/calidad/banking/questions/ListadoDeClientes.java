package co.com.udea.calidad.banking.questions;

import co.com.udea.calidad.banking.models.FilaCliente;
import co.com.udea.calidad.banking.userinterfaces.ListadoClientes;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/** Contenido de la tabla Customers del administrador. */
public final class ListadoDeClientes {

    private ListadoDeClientes() {
    }

    public static Question<List<FilaCliente>> filas() {
        return Question.about("las filas del listado de clientes").answeredBy(actor ->
                ListadoClientes.FILAS.resolveAllFor(actor).stream()
                        .filter(WebElementFacade::isCurrentlyVisible)
                        .map(ListadoDeClientes::aFila)
                        .collect(Collectors.toList()));
    }

    public static Question<Integer> cantidad() {
        return Question.about("la cantidad de clientes listados").answeredBy(actor -> filas().answeredBy(actor).size());
    }

    public static Question<List<String>> nombresCompletos() {
        return Question.about("los nombres de los clientes listados").answeredBy(actor ->
                filas().answeredBy(actor).stream().map(FilaCliente::getNombreCompleto).collect(Collectors.toList()));
    }

    public static Question<List<String>> columnas() {
        return Question.about("las columnas del listado de clientes").answeredBy(actor ->
                ListadoClientes.COLUMNAS.resolveAllFor(actor).stream()
                        .map(columna -> columna.getText().trim())
                        .collect(Collectors.toList()));
    }

    /** Valores de una columna (0 = First Name, 1 = Last Name, 2 = Post Code) en el orden mostrado. */
    public static Question<List<String>> valoresDeLaColumna(String columna) {
        return Question.about("los valores de la columna " + columna).answeredBy(actor ->
                filas().answeredBy(actor).stream().map(fila -> {
                    switch (columna) {
                        case "First Name":
                            return fila.getNombre();
                        case "Last Name":
                            return fila.getApellido();
                        case "Post Code":
                            return fila.getCodigoPostal();
                        default:
                            throw new IllegalArgumentException("Columna no ordenable: " + columna);
                    }
                }).collect(Collectors.toList()));
    }

    public static Question<Boolean> todasLasFilasTienenElBoton(String texto) {
        return Question.about("si cada cliente tiene el botón " + texto).answeredBy(actor -> {
            List<WebElementFacade> filas = ListadoClientes.FILAS.resolveAllFor(actor);
            return !filas.isEmpty() && filas.stream().allMatch(fila ->
                    fila.findElements(By.xpath(".//button")).stream()
                            .anyMatch(boton -> boton.getText().trim().equals(texto)));
        });
    }

    private static FilaCliente aFila(WebElementFacade fila) {
        List<WebElement> celdas = fila.findElements(By.tagName("td"));
        return new FilaCliente(texto(celdas, 0), texto(celdas, 1), texto(celdas, 2), texto(celdas, 3));
    }

    private static String texto(List<WebElement> celdas, int indice) {
        return celdas.size() > indice ? celdas.get(indice).getText().trim() : "";
    }
}
