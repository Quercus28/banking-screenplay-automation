package co.com.udea.calidad.banking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/** Fila del historial de movimientos de una cuenta. */
public class Movimiento {

    private static final DateTimeFormatter FORMATO_MEDIUM =
            DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a", Locale.US);

    private final String fecha;
    private final String monto;
    private final String tipo;

    public Movimiento(String fecha, String monto, String tipo) {
        this.fecha = fecha;
        this.monto = monto;
        this.tipo = tipo;
    }

    public static Movimiento de(String monto, String tipo) {
        return new Movimiento("", monto, tipo);
    }

    public LocalDateTime getFechaHora() {
        return LocalDateTime.parse(fecha, FORMATO_MEDIUM);
    }

    public String getMonto() {
        return monto;
    }

    public String getTipo() {
        return tipo;
    }

    /** Dos movimientos son iguales si coinciden monto y tipo; la fecha depende del momento de ejecución. */
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Movimiento)) {
            return false;
        }
        Movimiento movimiento = (Movimiento) otro;
        return monto.equals(movimiento.monto) && tipo.equals(movimiento.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, tipo);
    }

    @Override
    public String toString() {
        return monto + " " + tipo;
    }
}
