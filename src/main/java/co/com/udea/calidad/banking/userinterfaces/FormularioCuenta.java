package co.com.udea.calidad.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Pestaña Open Account del administrador. */
public final class FormularioCuenta {

    public static final String SIN_CLIENTE = "---Customer Name---";
    public static final String SIN_MONEDA = "---Currency---";

    public static final Target CLIENTE = Target.the("selector Customer").locatedBy("#userSelect");
    public static final Target MONEDA = Target.the("selector Currency").locatedBy("#currency");
    public static final Target PROCESAR = Target.the("botón Process")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");

    private FormularioCuenta() {
    }

    public static Target campo(String etiqueta) {
        switch (etiqueta) {
            case "Customer":
                return CLIENTE;
            case "Currency":
                return MONEDA;
            default:
                throw new IllegalArgumentException("Campo desconocido en Open Account: " + etiqueta);
        }
    }
}
