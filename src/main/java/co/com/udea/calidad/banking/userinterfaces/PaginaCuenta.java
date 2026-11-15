package co.com.udea.calidad.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Vista de la cuenta del cliente. */
public final class PaginaCuenta {

    private static final String INFORMACION_XPATH =
            "//div[contains(@class,'center') and contains(normalize-space(.),'Account Number')]";

    public static final Target BIENVENIDA = Target.the("mensaje de bienvenida")
            .locatedBy("//strong[contains(normalize-space(.),'Welcome')]");
    public static final Target CUENTAS = Target.the("selector de cuentas").locatedBy("#accountSelect");
    public static final Target INFORMACION = Target.the("información de la cuenta").locatedBy(INFORMACION_XPATH);
    public static final Target NUMERO = Target.the("número de cuenta").locatedBy(INFORMACION_XPATH + "/strong[1]");
    public static final Target SALDO = Target.the("saldo").locatedBy(INFORMACION_XPATH + "/strong[2]");
    public static final Target MONEDA = Target.the("moneda").locatedBy(INFORMACION_XPATH + "/strong[3]");

    private PaginaCuenta() {
    }
}
