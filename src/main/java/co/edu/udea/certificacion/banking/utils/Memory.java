package co.edu.udea.certificacion.banking.utils;

import net.serenitybdd.screenplay.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.edu.udea.certificacion.banking.utils.Constants.ACCOUNTS_BY_CUSTOMER;

/** Access to the data the actor remembers during a scenario. */
public final class Memory {

    private Memory() {
    }

    public static <T> List<T> list(Actor actor, String key) {
        List<T> values = actor.recall(key);
        if (values == null) {
            values = new ArrayList<>();
            actor.remember(key, values);
        }
        return values;
    }

    public static Map<String, List<String>> accountsByCustomer(Actor actor) {
        Map<String, List<String>> accounts = actor.recall(ACCOUNTS_BY_CUSTOMER);
        if (accounts == null) {
            accounts = new HashMap<>();
            actor.remember(ACCOUNTS_BY_CUSTOMER, accounts);
        }
        return accounts;
    }
}
