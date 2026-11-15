package co.edu.udea.certificacion.banking.utils;

import net.serenitybdd.screenplay.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.edu.udea.certificacion.banking.utils.Constantes.CUENTAS_POR_CLIENTE;

/** Acceso a los datos que el actor recuerda durante un escenario. */
public final class Memoria {

    private Memoria() {
    }

    public static <T> List<T> lista(Actor actor, String clave) {
        List<T> valores = actor.recall(clave);
        if (valores == null) {
            valores = new ArrayList<>();
            actor.remember(clave, valores);
        }
        return valores;
    }

    public static Map<String, List<String>> cuentasPorCliente(Actor actor) {
        Map<String, List<String>> cuentas = actor.recall(CUENTAS_POR_CLIENTE);
        if (cuentas == null) {
            cuentas = new HashMap<>();
            actor.remember(CUENTAS_POR_CLIENTE, cuentas);
        }
        return cuentas;
    }
}
