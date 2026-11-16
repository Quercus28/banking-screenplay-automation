package co.edu.udea.certificacion.banking.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/** Values read from serenity.conf. */
public final class Configuration {

    private static final String BANK_URL_PROPERTY = "pages.swaglabsUrl";

    private Configuration() {
    }

    /** Base address of XYZ Bank, ending with "/". */
    public static String bankUrl() {
        return EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(BANK_URL_PROPERTY);
    }
}
