package co.com.udea.calidad.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Pestaña Add Customer del administrador. */
public final class FormularioCliente {

    public static final Target NOMBRE = Target.the("campo First Name").locatedBy("//input[@ng-model='fName']");
    public static final Target APELLIDO = Target.the("campo Last Name").locatedBy("//input[@ng-model='lName']");
    public static final Target CODIGO_POSTAL = Target.the("campo Post Code").locatedBy("//input[@ng-model='postCd']");
    public static final Target AGREGAR = Target.the("botón Add Customer del formulario")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");

    private FormularioCliente() {
    }

    public static Target campo(String etiqueta) {
        switch (etiqueta) {
            case "First Name":
                return NOMBRE;
            case "Last Name":
                return APELLIDO;
            case "Post Code":
                return CODIGO_POSTAL;
            default:
                throw new IllegalArgumentException("Campo desconocido en Add Customer: " + etiqueta);
        }
    }
}
