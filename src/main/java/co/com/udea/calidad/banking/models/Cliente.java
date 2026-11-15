package co.com.udea.calidad.banking.models;

import java.util.Map;

public class Cliente {

    private final String nombre;
    private final String apellido;
    private final String codigoPostal;

    public Cliente(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    public static Cliente desde(Map<String, String> fila) {
        return new Cliente(fila.get("nombre"), fila.get("apellido"), fila.get("codigo_postal"));
    }

    /** Separa "Ana QAPrueba" en nombre "Ana" y apellido "QAPrueba". */
    public static Cliente llamado(String nombreCompleto, String codigoPostal) {
        String[] partes = nombreCompleto.trim().split(" ", 2);
        return new Cliente(partes[0], partes.length > 1 ? partes[1] : "", codigoPostal);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
