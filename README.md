# banking-screenplay-automation

Taller de automatización — Calidad de Software 2026-2 (UdeA).
Pruebas automatizadas de la aplicación [GlobalSQA Banking Project](https://www.globalsqa.com/angularJs-protractor/BankingProject/) con **Serenity BDD**, el patrón **Screenplay** y **Cucumber** (Gherkin).

---

## Requisitos

| Herramienta | Versión |
|---|---|
| JDK | 17 o superior (`java -version`) |
| Google Chrome | Cualquier versión reciente |
| Gradle | No hace falta instalarlo: se usa el wrapper `gradlew` incluido |

El driver de Chrome se descarga solo (`webdriver.autodownload = true` en `serenity.conf`). La primera ejecución necesita internet para bajar dependencias y el driver.

---

## Cómo correr las pruebas

Hay dos comandos. Los dos limpian el proyecto, ejecutan todos los escenarios y generan el reporte de Serenity. La única diferencia es si se muestra o no el **panel de demo** (ver más abajo).

### Sin demo (ejecución normal, rápida)

```bash
# Windows (PowerShell o CMD)
.\gradlew.bat serenity

# Linux / macOS / Git Bash
./gradlew serenity
```

Es la forma de correr la suite para verificar resultados: el navegador avanza a velocidad normal, sin pausas.

### Con demo (para presentar o explicar en clase)

```bash
# Windows (PowerShell o CMD)
.\gradlew.bat serenityDemo

# Linux / macOS / Git Bash
./gradlew serenityDemo
```

El navegador muestra el panel de demo y se pausa en cada paso para que se pueda seguir lo que hace la prueba. Toda la suite tarda bastante más que en modo normal; para una presentación conviene filtrar por tags (ver abajo).

### Correr solo algunos escenarios (tags)

Los dos comandos aceptan un filtro de tags de Cucumber con `-Dtags`:

```bash
# Una historia de usuario
.\gradlew.bat serenity -Dtags="@HU-08"

# Un escenario explorado concreto, con demo
.\gradlew.bat serenityDemo -Dtags="@EXP-035"

# Combinaciones
.\gradlew.bat serenity -Dtags="@manager and @happy-path"
.\gradlew.bat serenity -Dtags="not @defect"
```

En PowerShell, deja el valor de `-Dtags` entre comillas.

Tags disponibles en los `.feature`:

| Tag | Significado |
|---|---|
| `@home`, `@manager`, `@customer` | Área de la aplicación (inicio, gerente del banco, cliente) |
| `@HU-01` … `@HU-12` | Historia de usuario |
| `@CA-HUxx-yy` | Criterio de aceptación |
| `@EXP-001` … `@EXP-055` | Escenario de la exploración manual (`docs/03_Resultados_Exploracion/Matriz_Exploracion.md`) |
| `@HAL-01` … `@HAL-17` | Hallazgo relacionado (`docs/03_Resultados_Exploracion/Hallazgos.md`) |
| `@happy-path`, `@alternative`, `@negative` | Tipo de escenario |
| `@defect` | Escenario que documenta un defecto conocido de la aplicación |

### Reporte

Al terminar, el reporte queda en:

```
target/site/serenity/index.html
```

Ábrelo en el navegador. El reporte se genera **aunque fallen escenarios**; en ese caso la tarea termina en error al final, indicando cuántos escenarios fallaron.

> **Fallos esperados:** los escenarios con `@defect` describen el comportamiento *correcto* que la aplicación debería tener y no tiene (hallazgos HAL-xx). Por eso fallan a propósito: el fallo es la evidencia del defecto. Para una corrida “en verde” usa `-Dtags="not @defect"`.

### ¿Y `gradlew test`?

`.\gradlew.bat test` también ejecuta los escenarios, pero no genera el reporte agregado y usa tal cual lo que diga `serenity.conf` (donde la demo está activada por defecto, `demo.overlay.enabled = true`). Para el día a día usa `serenity` o `serenityDemo`, que deciden por ti si la demo va activa o no.

---

## ¿Qué es la demo?

La demo es una **ayuda didáctica** que se agregó al proyecto para poder explicar en vivo cómo funciona Screenplay. Cuando está activa, dibuja un panel oscuro en la parte inferior del navegador mientras corre la prueba. Ejemplo ilustrativo:

```
┌──────────────────────────────────────────────────────────────────────┐
│ Deposits  ›  Deposit a valid amount                       (escenario) │
│ [When]  the customer deposits 100                     (paso Gherkin)  │
│   ↳ Customer makes a deposit of 100                   (Task)          │
│     ↳ Enter "100" into the amount field               (Interaction)   │
└──────────────────────────────────────────────────────────────────────┘
```

- **Primera línea:** feature y escenario en ejecución.
- **Segunda línea:** el paso Gherkin actual, con una etiqueta de color según el tipo: azul para `Given`, naranja para `When`, morado para `Then` (`And`/`But` heredan el color del paso anterior).
- **Líneas con `↳`:** las *Tasks* e *Interactions* de Screenplay que componen ese paso, anidadas según quién llama a quién.
- **Al terminar el escenario:** el borde del panel se pone verde si pasó o rojo si falló.

Además, la ejecución se **pausa** después de cada paso para que el público alcance a leer. Así se ve la relación directa entre el lenguaje de negocio (Gherkin), las tareas del actor (Screenplay) y lo que pasa en el navegador.

La demo no cambia lo que se prueba ni los resultados: solo muestra información y agrega pausas. Tampoco toca las alertas nativas del navegador (cuando hay un `alert` abierto, el panel no se redibuja para no cerrarlo).

### Cómo está implementada

| Archivo | Rol |
|---|---|
| `src/test/java/.../demo/DemoOverlay.java` | Dibuja el panel en la página con JavaScript y maneja las pausas |
| `src/test/java/.../demo/DemoPlugin.java` | Plugin de Cucumber (registrado en `Runner.java`): avisa qué escenario y qué paso Gherkin empiezan |
| `src/test/java/.../demo/DemoStepListener.java` | Listener de Serenity: avisa qué Task o Interaction de Screenplay se está ejecutando |
| `src/test/java/.../stepdefinitions/DemoHooks.java` | Hooks `@Before`/`@After` que registran el listener cuando la demo está activa |

### Configuración (`src/test/resources/serenity.conf`)

```hocon
demo {
  overlay {
    enabled = true          # gradlew serenity / serenityDemo lo sobreescriben
    step.pause.ms = 1800    # pausa después de cada paso Gherkin
    action.pause.ms = 700   # pausa después de cada Task / Interaction
    result.pause.ms = 3000  # tiempo que se muestra el resultado del escenario
  }
}
```

Si la demo va muy rápida o muy lenta, ajusta las pausas aquí.

---

## Estructura del proyecto

```
src/main/java/co/edu/udea/certificacion/banking/
├── tasks/            Tareas de negocio del actor (LoginAsCustomer, MakeTransaction, RegisterCustomer…)
├── interactions/     Acciones de bajo nivel (EnterText, PressButton, CaptureAlert, OpenRoute…)
├── questions/        Preguntas para verificar el estado (LastAlert, CustomerList, TransactionHistory…)
├── userinterfaces/   Localizadores de cada pantalla (BankPage, AccountPage, AddCustomerForm…)
├── models/           Objetos de datos (Customer, Transaction, CustomerRow)
└── utils/            Constantes, configuración y utilidades

src/test/java/co/edu/udea/certificacion/banking/
├── runners/Runner.java   Runner de Cucumber + Serenity
├── stepdefinitions/      Glue entre los pasos Gherkin y Screenplay
└── demo/                 Panel de demo (ver arriba)

src/test/resources/
├── features/
│   ├── home/             Navegación en la pantalla de inicio
│   ├── manager/          Registrar clientes, abrir cuentas, buscar y eliminar clientes
│   └── customer/         Login, logout, depósitos, retiros, saldo, transacciones, filtros, reset
└── serenity.conf         Navegador, URL de la app y configuración de la demo

docs/                     Exploración manual, capturas, historias de usuario e informe (ver docs/README.md)
```

La suite tiene **13 features** y **62 escenarios**, trazados a 12 historias de usuario (HU-01 a HU-12) y a los escenarios de la exploración manual (EXP-xxx).

---

## Problemas comunes

- **`JAVA_HOME is not set` / versión de Java:** instala un JDK 17+ y configura `JAVA_HOME`.
- **El navegador no abre o falla el driver:** actualiza Chrome y vuelve a ejecutar; el driver se descarga de nuevo según la versión instalada.
- **Ejecutar sin ventana (CI):** descomenta `#headless.mode = true` en `serenity.conf`. No tiene sentido combinarlo con la demo.
- **Escenarios de filtro por fecha que “se quedan quietos”:** el filtro de transacciones trabaja por minutos, así que algunos escenarios esperan al inicio del siguiente minuto (`WaitForNextMinute`) para separar transacciones. Pueden quedarse hasta un minuto sin hacer nada; es normal.
