package co.edu.udea.certificacion.banking.demo;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Teaching aid: draws a panel at the bottom of the browser that shows the scenario being run, the
 * current Gherkin step and the Screenplay tasks and interactions it is made of, and pauses after each
 * step so the class can follow it. It is turned on and configured in serenity.conf (demo.overlay.*).
 */
public final class DemoOverlay {

    private static final EnvironmentSpecificConfiguration CONFIG =
            EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables());

    private static final boolean ENABLED = CONFIG.getBooleanProperty("demo.overlay.enabled", false);
    private static final long GHERKIN_STEP_PAUSE_MS = pauseSetting("demo.overlay.step.pause.ms", 1800);
    private static final long SCREENPLAY_STEP_PAUSE_MS = pauseSetting("demo.overlay.action.pause.ms", 700);
    private static final long RESULT_PAUSE_MS = pauseSetting("demo.overlay.result.pause.ms", 3000);

    private static final String GIVEN_COLOR = "#2e86c1";
    private static final String WHEN_COLOR = "#d68910";
    private static final String THEN_COLOR = "#8e44ad";
    private static final String PASSED_COLOR = "#1e8449";
    private static final String FAILED_COLOR = "#c0392b";

    private static final String RENDER_SCRIPT = String.join("\n",
            "if (!document.body) { return; }",
            "var panel = document.getElementById('demo-overlay');",
            "if (!panel) { panel = document.createElement('div'); panel.id = 'demo-overlay'; document.body.appendChild(panel); }",
            "panel.style.cssText = 'position:fixed;left:0;right:0;bottom:0;z-index:2147483647;pointer-events:none;'",
            "  + 'font-family:Segoe UI,Arial,sans-serif;background:rgba(17,24,39,.93);color:#fff;padding:12px 22px 14px;'",
            "  + 'box-shadow:0 -4px 18px rgba(0,0,0,.35);border-top:6px solid ' + arguments[0] + ';';",
            "panel.textContent = '';",
            "function line(text, css) { var d = document.createElement('div'); d.textContent = text; d.style.cssText = css; panel.appendChild(d); return d; }",
            "line(arguments[1], 'font-size:14px;opacity:.75;margin-bottom:4px;');",
            "var step = line('', 'font-size:22px;font-weight:600;line-height:1.3;');",
            "var badge = document.createElement('span'); badge.textContent = arguments[2];",
            "badge.style.cssText = 'background:' + arguments[0] + ';border-radius:4px;padding:1px 10px;margin-right:10px;font-size:15px;vertical-align:middle;';",
            "step.appendChild(badge); step.appendChild(document.createTextNode(arguments[3]));",
            "var details = arguments[4];",
            "for (var i = 0; i < details.length; i++) {",
            "  line('\\u21b3 ' + details[i], 'font-size:15px;opacity:.9;margin-top:3px;padding-left:' + (14 + i * 18) + 'px;');",
            "}");

    private static boolean active;
    private static String header = "";
    private static String badge = "";
    private static String stepText = "";
    private static String color = GIVEN_COLOR;
    private static final Deque<String> screenplaySteps = new ArrayDeque<>();

    private DemoOverlay() {
    }

    /** True when demo.overlay.enabled = true in serenity.conf. */
    public static boolean isEnabled() {
        return ENABLED;
    }

    private static long pauseSetting(String property, long defaultMs) {
        try {
            return CONFIG.getOptionalProperty(property).map(String::trim).map(Long::parseLong).orElse(defaultMs);
        } catch (NumberFormatException invalidNumber) {
            return defaultMs;
        }
    }

    static void startScenario(String feature, String scenario) {
        active = true;
        header = feature + "  ›  " + scenario;
        badge = "";
        stepText = "";
        screenplaySteps.clear();
    }

    static boolean isActive() {
        return active;
    }

    /** A Gherkin step of the .feature file starts. */
    static void gherkinStep(int number, int total, String keyword, String type, String text) {
        if (!active) {
            return;
        }
        badge = "Step " + number + " of " + total + " · " + keyword.toUpperCase();
        stepText = text;
        color = colorFor(type);
        screenplaySteps.clear();
        renderAndPause(GHERKIN_STEP_PAUSE_MS);
    }

    /** A Screenplay task or interaction (an @Step method) starts inside the current Gherkin step. */
    static void screenplayStepStarted(String title) {
        if (!active) {
            return;
        }
        screenplaySteps.push(title);
        if (title.endsWith(stepText)) {
            // Serenity also reports the Gherkin step itself; it is already shown on the main line.
            return;
        }
        renderAndPause(SCREENPLAY_STEP_PAUSE_MS);
    }

    static void screenplayStepFinished() {
        if (active && !screenplaySteps.isEmpty()) {
            screenplaySteps.pop();
        }
    }

    static void finishScenario(boolean passed) {
        if (!active) {
            return;
        }
        badge = passed ? "PASSED" : "FAILED";
        stepText = passed ? "The scenario passed" : "The scenario failed";
        color = passed ? PASSED_COLOR : FAILED_COLOR;
        screenplaySteps.clear();
        renderAndPause(RESULT_PAUSE_MS);
        active = false;
    }

    static void stop() {
        active = false;
        screenplaySteps.clear();
    }

    private static String colorFor(String type) {
        switch (type) {
            case "When":
                return WHEN_COLOR;
            case "Then":
                return THEN_COLOR;
            default:
                return GIVEN_COLOR;
        }
    }

    private static void renderAndPause(long pauseMs) {
        if (render()) {
            pause(pauseMs);
        }
    }

    /** Draws the panel. Returns false when there is no browser yet or it cannot be drawn right now. */
    private static boolean render() {
        if (!ThucydidesWebDriverSupport.isDriverInstantiated()) {
            return false;
        }
        try {
            WebDriver driver = ThucydidesWebDriverSupport.getDriver();
            if (alertIsOpen(driver)) {
                // Running JavaScript while a native alert is open would make WebDriver dismiss it,
                // and the steps that read the alert text would fail. The alert stays visible instead.
                return true;
            }
            ((JavascriptExecutor) driver).executeScript(RENDER_SCRIPT, color, header, badge, stepText, details());
            return true;
        } catch (RuntimeException couldNotDraw) {
            // The page may be loading or closing: the panel is drawn again on the next step.
            return false;
        }
    }

    private static boolean alertIsOpen(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException noAlert) {
            return false;
        }
    }

    /** Screenplay steps from the outermost to the innermost, without the Gherkin step itself. */
    private static List<String> details() {
        List<String> details = new ArrayList<>();
        screenplaySteps.descendingIterator().forEachRemaining(title -> {
            if (!title.endsWith(stepText)) {
                details.add(title);
            }
        });
        return details;
    }

    private static void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException interruption) {
            Thread.currentThread().interrupt();
        }
    }
}
