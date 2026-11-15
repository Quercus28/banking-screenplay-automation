package co.com.udea.calidad.banking.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Fila del listado de clientes del administrador. */
public class FilaCliente {

    private final String nombre;
    private final String apellido;
    private final String codigoPostal;
    private final String cuentas;

    public FilaCliente(String nombre, String apellido, String codigoPostal, String cuentas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
        this.cuentas = cuentas;
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

    public String getCuentas() {
        return cuentas;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public List<String> getListaCuentas() {
        return cuentas.isEmpty() ? Collections.emptyList() : Arrays.asList(cuentas.split("\s+"));
    }

    public boolean es(String nombre, String apellido) {
        return this.nombre.equals(nombre) && this.apellido.equals(apellido);
    }

    @Override
    public String toString() {
        return nombre + " | " + apellido + " | " + codigoPostal + " | " + cuentas;
    }
}
