package co.com.udea.calidad.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Vista Transactions de la cuenta. */
public final class PaginaMovimientos {

    public static final Target FECHA_INICIO = Target.the("filtro de fecha inicial").locatedBy("#start");
    public static final Target FECHA_FIN = Target.the("filtro de fecha final").locatedBy("#end");
    public static final Target FILAS = Target.the("filas del historial").locatedBy("//table/tbody/tr");

    private PaginaMovimientos() {
    }
}
