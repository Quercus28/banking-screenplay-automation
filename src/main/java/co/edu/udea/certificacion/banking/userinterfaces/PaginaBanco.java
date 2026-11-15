package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

import static co.edu.udea.certificacion.banking.utils.XPath.literal;

/** Elementos comunes a todas las vistas de XYZ Bank. */
public final class PaginaBanco {

    public static final Target CUERPO = Target.the("cuerpo de la página").locatedBy("//body");
    public static final Target MENSAJES_DE_ERROR = Target.the("mensajes de error")
            .locatedBy("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'help-block')]");

    private PaginaBanco() {
    }

    public static Target boton(String texto) {
        return Target.the("botón " + texto).locatedBy("//button[normalize-space()=" + literal(texto) + "]");
    }

    public static Target mensaje(String texto) {
        return Target.the("mensaje " + texto).locatedBy("//span[contains(normalize-space(.)," + literal(texto) + ")]");
    }

    public static Target botonQueContiene(String texto) {
        return Target.the("botón que contiene " + texto)
                .locatedBy("//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                        + "'abcdefghijklmnopqrstuvwxyz')," + literal(texto.toLowerCase()) + ")]");
    }

    public static Target encabezadoOrdenable(String columna) {
        return Target.the("encabezado " + columna).locatedBy("//thead//a[normalize-space()=" + literal(columna) + "]");
    }
}
