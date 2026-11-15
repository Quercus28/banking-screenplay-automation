package co.com.udea.calidad.banking.questions;

import net.serenitybdd.screenplay.Question;

import static co.com.udea.calidad.banking.utils.Constantes.ULTIMA_ALERTA;

/** Texto de la última alerta nativa; vacío si no apareció ninguna. */
public final class UltimaAlerta {

    private UltimaAlerta() {
    }

    public static Question<String> texto() {
        return Question.about("el texto de la última alerta").answeredBy(actor -> {
            String texto = actor.recall(ULTIMA_ALERTA);
            return texto == null ? "" : texto;
        });
    }
}
