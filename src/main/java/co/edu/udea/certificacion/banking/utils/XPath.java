package co.edu.udea.certificacion.banking.utils;

public final class XPath {

    private XPath() {
    }

    /** Turns a text into a valid XPath literal, even when it contains quotes. */
    public static String literal(String text) {
        if (!text.contains("'")) {
            return "'" + text + "'";
        }
        if (!text.contains("\"")) {
            return "\"" + text + "\"";
        }
        return "concat('" + text.replace("'", "',\"'\",'") + "')";
    }
}
