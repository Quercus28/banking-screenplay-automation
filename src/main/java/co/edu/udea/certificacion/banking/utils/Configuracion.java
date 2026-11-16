package co.edu.udea.certificacion.banking.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/** Valores que se leen de serenity.conf. */
public final class Configuracion {

    private static final String PROPIEDAD_URL_BANCO = "pages.swaglabsUrl";

    private Configuracion() {
    }

    /** Dirección base de XYZ Bank, terminada en "/". */
    public static String urlBanco() {
        return EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(PROPIEDAD_URL_BANCO);
    }
}
