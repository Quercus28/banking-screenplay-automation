package co.edu.udea.certificacion.banking.utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Comparator;
import java.util.List;

/**
 * Matchers para comprobar el orden de una lista. Los textos se comparan como lo hace el filtro
 * orderBy de AngularJS 1.4: sin distinguir mayúsculas y minúsculas.
 */
public final class Orden {

    private static final Comparator<String> TEXTO_ANGULAR = Comparator.comparing(String::toLowerCase);

    private Orden() {
    }

    public static Matcher<List<String>> textosAscendentes() {
        return ordenada(TEXTO_ANGULAR, "ascendente");
    }

    public static Matcher<List<String>> textosDescendentes() {
        return ordenada(TEXTO_ANGULAR.reversed(), "descendente");
    }

    public static <T extends Comparable<? super T>> Matcher<List<T>> ascendente() {
        return ordenada(Comparator.<T>naturalOrder(), "ascendente");
    }

    public static <T extends Comparable<? super T>> Matcher<List<T>> descendente() {
        return ordenada(Comparator.<T>reverseOrder(), "descendente");
    }

    private static <T> Matcher<List<T>> ordenada(Comparator<T> comparador, String sentido) {
        return new TypeSafeMatcher<List<T>>() {
            @Override
            protected boolean matchesSafely(List<T> valores) {
                for (int i = 1; i < valores.size(); i++) {
                    if (comparador.compare(valores.get(i - 1), valores.get(i)) > 0) {
                        return false;
                    }
                }
                return valores.size() > 1;
            }

            @Override
            public void describeTo(Description descripcion) {
                descripcion.appendText("una lista de al menos dos valores en orden " + sentido);
            }
        };
    }
}
