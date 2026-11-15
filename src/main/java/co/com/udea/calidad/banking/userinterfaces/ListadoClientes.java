package co.com.udea.calidad.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

import static co.com.udea.calidad.banking.utils.XPath.literal;

/** Pestaña Customers del administrador. */
public final class ListadoClientes {

    public static final Target BUSCADOR = Target.the("campo Search Customer")
            .locatedBy("//input[@ng-model='searchCustomer']");
    public static final Target FILAS = Target.the("filas del listado de clientes").locatedBy("//table/tbody/tr");
    public static final Target COLUMNAS = Target.the("columnas del listado de clientes").locatedBy("//table/thead//td");

    private ListadoClientes() {
    }

    public static Target botonEliminar(String nombre, String apellido) {
        return Target.the("botón Delete de " + nombre + " " + apellido)
                .locatedBy("//tbody/tr[td[1][normalize-space()=" + literal(nombre) + "] and td[2][normalize-space()="
                        + literal(apellido) + "]]//button[normalize-space()='Delete']");
    }
}
