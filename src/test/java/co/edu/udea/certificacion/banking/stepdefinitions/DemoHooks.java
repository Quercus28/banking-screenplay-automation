package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.demo.DemoOverlay;
import co.edu.udea.certificacion.banking.demo.DemoStepListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.thucydides.core.steps.StepEventBus;

/** Turns the teaching panel on for every scenario when demo.overlay.enabled = true in serenity.conf. */
public class DemoHooks {

    private final DemoStepListener listener = new DemoStepListener();

    @Before
    public void showDemoOverlay() {
        if (DemoOverlay.isEnabled()) {
            StepEventBus.getEventBus().registerListener(listener);
        }
    }

    @After
    public void hideDemoOverlay() {
        if (DemoOverlay.isEnabled()) {
            StepEventBus.getEventBus().dropListener(listener);
        }
    }
}
