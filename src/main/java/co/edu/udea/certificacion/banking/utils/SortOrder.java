package co.edu.udea.certificacion.banking.utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Comparator;
import java.util.List;

/**
 * Matchers that check the order of a list. Texts are compared the way the AngularJS 1.4 orderBy
 * filter does it: case-insensitively.
 */
public final class SortOrder {

    private static final Comparator<String> ANGULAR_TEXT = Comparator.comparing(String::toLowerCase);

    private SortOrder() {
    }

    public static Matcher<List<String>> textsAscending() {
        return sorted(ANGULAR_TEXT, "ascending");
    }

    public static Matcher<List<String>> textsDescending() {
        return sorted(ANGULAR_TEXT.reversed(), "descending");
    }

    public static <T extends Comparable<? super T>> Matcher<List<T>> ascending() {
        return sorted(Comparator.<T>naturalOrder(), "ascending");
    }

    public static <T extends Comparable<? super T>> Matcher<List<T>> descending() {
        return sorted(Comparator.<T>reverseOrder(), "descending");
    }

    private static <T> Matcher<List<T>> sorted(Comparator<T> comparator, String direction) {
        return new TypeSafeMatcher<List<T>>() {
            @Override
            protected boolean matchesSafely(List<T> values) {
                for (int i = 1; i < values.size(); i++) {
                    if (comparator.compare(values.get(i - 1), values.get(i)) > 0) {
                        return false;
                    }
                }
                return values.size() > 1;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a list of at least two values in " + direction + " order");
            }
        };
    }
}
