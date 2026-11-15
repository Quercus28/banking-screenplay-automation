package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EscribirFechaHora;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.PaginaMovimientos.FECHA_FIN;
import static co.edu.udea.certificacion.banking.userinterfaces.PaginaMovimientos.FECHA_INICIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Aplica el filtro de fechas del historial. Un valor vacío deja ese extremo sin cambios. */
public class FiltrarMovimientos implements Task {

    private final String desde;
    private final String hasta;

    public FiltrarMovimientos(String desde, String hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public static FiltrarMovimientos entre(String desde, String hasta) {
        return instrumented(FiltrarMovimientos.class, desde, hasta);
    }

    public static FiltrarMovimientos hasta(String hasta) {
        return entre("", hasta);
    }

    @Override
    @Step("{0} filtra los movimientos desde '#desde' hasta '#hasta'")
    public <T extends Actor> void performAs(T actor) {
        if (!desde.isEmpty()) {
            actor.attemptsTo(EscribirFechaHora.con(desde, FECHA_INICIO));
        }
        if (!hasta.isEmpty()) {
            actor.attemptsTo(EscribirFechaHora.con(hasta, FECHA_FIN));
        }
    }
}
