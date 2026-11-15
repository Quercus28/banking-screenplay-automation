package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Vista Customer Login. */
public final class InicioSesionCliente {

    public static final String SIN_CLIENTE = "---Your Name---";

    public static final Target CLIENTE = Target.the("selector Your Name").locatedBy("#userSelect");
    public static final Target LOGIN = Target.the("botón Login").locatedBy("//button[normalize-space()='Login']");

    private InicioSesionCliente() {
    }
}
