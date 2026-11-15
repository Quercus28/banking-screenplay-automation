package co.com.udea.calidad.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.time.LocalTime;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Espera hasta el primer segundo del minuto siguiente. El filtro de movimientos trabaja por minutos,
 * así que se usa para separar operaciones en minutos distintos.
 */
public class EsperarSiguienteMinuto implements Interaction {

    public static EsperarSiguienteMinuto completo() {
        return instrumented(EsperarSiguienteMinuto.class);
    }

    @Override
    @Step("{0} espera al minuto siguiente")
    public <T extends Actor> void performAs(T actor) {
        long segundosRestantes = 60L - LocalTime.now().getSecond() + 1L;
        try {
            Thread.sleep(segundosRestantes * 1000L);
        } catch (InterruptedException interrupcion) {
            Thread.currentThread().interrupt();
        }
    }
}
