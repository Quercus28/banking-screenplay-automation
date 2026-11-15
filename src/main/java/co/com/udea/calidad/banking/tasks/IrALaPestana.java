package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import co.com.udea.calidad.banking.userinterfaces.FormularioCliente;
import co.com.udea.calidad.banking.userinterfaces.FormularioCuenta;
import co.com.udea.calidad.banking.userinterfaces.ListadoClientes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/** Abre una pestaña del administrador: Add Customer, Open Account o Customers. */
public class IrALaPestana implements Task {

    private final String pestana;

    public IrALaPestana(String pestana) {
        this.pestana = pestana;
    }

    public static IrALaPestana delAdministrador(String pestana) {
        return instrumented(IrALaPestana.class, pestana);
    }

    @Override
    @Step("{0} abre la pestaña '#pestana' del administrador")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(ElementoVisible.de(boton(pestana)))) {
            actor.attemptsTo(IngresarComoAdministrador.alBanco());
        }
        actor.attemptsTo(
                PulsarBoton.llamado(pestana),
                WaitUntil.the(contenidoDe(pestana), isVisible()).forNoMoreThan(10).seconds()
        );
    }

    private static Target contenidoDe(String pestana) {
        switch (pestana) {
            case "Add Customer":
                return FormularioCliente.NOMBRE;
            case "Open Account":
                return FormularioCuenta.MONEDA;
            case "Customers":
                return ListadoClientes.BUSCADOR;
            default:
                throw new IllegalArgumentException("Pestaña desconocida del administrador: " + pestana);
        }
    }
}
