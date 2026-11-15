package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Formularios Deposit y Withdrawl de la vista de la cuenta. */
public final class FormularioTransaccion {

    public static final Target MONTO = Target.the("campo de monto").locatedBy("//input[@ng-model='amount']");
    public static final Target ENVIAR = Target.the("botón de envío de la transacción")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");
    public static final Target MENSAJE = Target.the("mensaje de la transacción").locatedBy("//span[contains(@class,'error')]");
    public static final Target ETIQUETA_DEPOSITO = Target.the("etiqueta Amount to be Deposited")
            .locatedBy("//label[contains(normalize-space(.),'Amount to be Deposited')]");
    public static final Target ETIQUETA_RETIRO = Target.the("etiqueta Amount to be Withdrawn")
            .locatedBy("//label[contains(normalize-space(.),'Amount to be Withdrawn')]");

    private FormularioTransaccion() {
    }
}
