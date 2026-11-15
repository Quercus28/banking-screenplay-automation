package co.com.udea.calidad.banking.utils;

public final class XPath {

    private XPath() {
    }

    /** Convierte un texto en un literal XPath válido aunque contenga comillas. */
    public static String literal(String texto) {
        if (!texto.contains("'")) {
            return "'" + texto + "'";
        }
        if (!texto.contains("\"")) {
            return "\"" + texto + "\"";
        }
        return "concat('" + texto.replace("'", "',\"'\",'") + "')";
    }
}
