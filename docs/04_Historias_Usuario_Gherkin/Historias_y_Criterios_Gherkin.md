# Historias de usuario y criterios de aceptación — GlobalSQA Banking

Taller 2, punto 3 · Calidad de Software 2026-2 · Universidad de Antioquia

> Representación editable del documento `Historias_Usuario_y_Criterios_Aceptacion_GlobalSQA.docx`. No es una implementación: aún no hay archivos `.feature` ni clases Java.

**Resumen:** 12 historias · 64 reglas de negocio · 49 criterios (14 camino feliz, 15 alternativos, 20 negativos) · 8 criterios que la aplicación no cumple hoy.

**Estado actual:** `Cumple` = la evidencia del punto 2 muestra el comportamiento. `No cumple (HAL-XX)` = el criterio describe lo esperado y el hallazgo muestra lo contrario; no se debe ajustar el criterio para que pase.

## Inventario

| ID | Nombre | Actor | Necesidad principal | Módulo |
|---|---|---|---|---|
| HU-01 | Registrar clientes | Administrador bancario | Inscribir a una persona como cliente del banco. | Administración bancaria |
| HU-02 | Abrir cuentas | Administrador bancario | Crear cuentas en una moneda para un cliente registrado. | Administración bancaria |
| HU-03 | Consultar y buscar clientes | Administrador bancario | Encontrar un cliente y revisar sus datos y cuentas. | Administración bancaria |
| HU-04 | Eliminar clientes | Administrador bancario | Retirar del banco a un cliente. | Administración bancaria |
| HU-05 | Ingresar como cliente | Cliente | Acceder a sus cuentas. | Operaciones del cliente |
| HU-06 | Consultar el saldo de cada cuenta | Cliente | Ver el saldo y la moneda de cada una de sus cuentas. | Operaciones del cliente |
| HU-07 | Cerrar sesión | Cliente | Salir de la banca protegiendo su información. | Operaciones del cliente |
| HU-08 | Depositar dinero | Cliente | Aumentar el saldo de una cuenta. | Operaciones del cliente |
| HU-09 | Retirar dinero | Cliente | Disponer del saldo de una cuenta. | Operaciones del cliente |
| HU-10 | Consultar movimientos | Cliente | Revisar los depósitos y retiros hechos en una cuenta. | Operaciones del cliente |
| HU-11 | Filtrar movimientos por fecha | Cliente | Revisar solo los movimientos de un período. | Operaciones del cliente |
| HU-12 | Reiniciar el historial de una cuenta | Cliente | Dejar una cuenta sin movimientos registrados. | Operaciones del cliente |

## HU-01 — Registrar clientes

**Historia de usuario**

Como administrador bancario, quiero registrar a un nuevo cliente con su nombre, apellido y código postal, para que quede inscrito en el banco y se le pueda abrir una cuenta.

**Reglas de negocio**

- **RN-HU01-01.** Para registrar un cliente se requieren su nombre, su apellido y su código postal.
- **RN-HU01-02.** Si falta alguno de esos datos, el registro no se completa y se indica cuál es el dato que falta.
- **RN-HU01-03.** Cuando el registro es exitoso, el sistema lo confirma e informa el identificador asignado al cliente.
- **RN-HU01-04.** Los identificadores de cliente se asignan de forma consecutiva.
- **RN-HU01-05.** El nombre y el apellido pueden contener tildes, eñes, guiones y apóstrofes, y se guardan tal como se escribieron.
- **RN-HU01-06.** No se puede registrar un cliente con el mismo nombre, apellido y código postal de otro que ya existe; en ese caso el sistema advierte que el cliente puede estar duplicado.
- **RN-HU01-07.** Dos clientes pueden tener el mismo nombre y apellido si su código postal es diferente.
- **RN-HU01-08.** Un dato que solo contiene espacios en blanco se considera vacío, y el mensaje debe indicar que faltan datos obligatorios. _Expectativa de calidad. Hoy el registro se bloquea, pero el mensaje habla de un posible duplicado (HAL-06)._
- **RN-HU01-09.** La validación de duplicados no debería distinguir entre mayúsculas y minúsculas. _Expectativa de calidad por confirmar (HAL-07). Hoy 'ana qaprueba' se registra aunque exista 'Ana QAPrueba' con el mismo código postal._
- **RN-HU01-10.** El nombre y el apellido no deberían aceptar valores compuestos solo por números. _Expectativa de calidad por confirmar (HAL-08). No se convierte en criterio automatizable hasta que el equipo la confirme._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU01-01 - Registrar un cliente con datos completos
    Given que el administrador se encuentra en el formulario de registro de clientes
    When registra al cliente "Ana" "QAPrueba" con el código postal "QA0001"
    Then se muestra una confirmación con el identificador asignado al cliente
      And el cliente aparece en el listado de clientes, todavía sin cuentas

Scenario: CA-HU01-02 - Impedir el registro sin ningún dato
    Given que el administrador se encuentra en el formulario de registro de clientes
    When intenta registrar un cliente sin diligenciar ningún dato
    Then el cliente no queda registrado
      And se indica que el nombre es un dato obligatorio

Scenario: CA-HU01-03 - Impedir el registro sin el código postal
    Given que el administrador se encuentra en el formulario de registro de clientes
    When diligencia el nombre y el apellido, pero deja vacío el código postal
      And intenta completar el registro
    Then el cliente no queda registrado
      And se indica que el código postal es un dato obligatorio

Scenario: CA-HU01-04 - Registrar un cliente con tildes, guiones y apóstrofes en el nombre
    Given que el administrador se encuentra en el formulario de registro de clientes
    When registra al cliente "José-María" "O'Brien Ñúñez" con el código postal "QA0005"
    Then se muestra una confirmación con el identificador asignado al cliente
      And el listado de clientes muestra el nombre y el apellido escritos exactamente igual

Scenario: CA-HU01-05 - Rechazar un cliente duplicado
    Given que existe el cliente "Ana" "QAPrueba" con el código postal "QA0001"
    When el administrador intenta registrar otro cliente con esos mismos datos
    Then se informa que el cliente puede estar duplicado
      And el listado sigue mostrando un solo cliente con esos datos

Scenario: CA-HU01-06 - Registrar un cliente con el mismo nombre y otro código postal
    Given que existe el cliente "Ana" "QAPrueba" con el código postal "QA0001"
    When el administrador registra al cliente "Ana" "QAPrueba" con el código postal "QA9999"
    Then se muestra una confirmación con un nuevo identificador
      And el listado muestra dos clientes llamados Ana QAPrueba con códigos postales diferentes

Scenario: CA-HU01-07 - Impedir el registro con datos que solo contienen espacios
    Given que el administrador se encuentra en el formulario de registro de clientes
    When escribe solo espacios en blanco en el nombre, el apellido y el código postal
      And intenta completar el registro
    Then el cliente no queda registrado
      And se indica que faltan datos obligatorios
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU01-01 | Camino feliz | RN-HU01-01, RN-HU01-03 | EXP-007, EXP-008 | — | Cumple |
| CA-HU01-02 | Negativo | RN-HU01-01, RN-HU01-02 | EXP-009 | — | Cumple |
| CA-HU01-03 | Negativo | RN-HU01-01, RN-HU01-02 | EXP-010 | — | Cumple |
| CA-HU01-04 | Alternativo | RN-HU01-05 | EXP-011 | — | Cumple |
| CA-HU01-05 | Negativo | RN-HU01-06 | EXP-014 | — | Cumple |
| CA-HU01-06 | Alternativo | RN-HU01-07 | EXP-015 | — | Cumple |
| CA-HU01-07 | Negativo | RN-HU01-02, RN-HU01-08 | EXP-013 | HAL-06 | No cumple (HAL-06) |

Consideraciones:

- CA-HU01-01: En un navegador con los datos iniciales, el primer cliente nuevo recibe el identificador 6 y el siguiente el 7. La confirmación es una alerta del navegador.
- CA-HU01-02: La indicación es la validación nativa del navegador; su texto depende del idioma del navegador.
- CA-HU01-03: EXP-010 mostró el mismo comportamiento cuando falta el apellido.
- CA-HU01-04: En EXP-011 también se aceptó un código postal compuesto solo por símbolos (ver HAL-08).
- CA-HU01-05: Mensaje observado: 'Please check the details. Customer may be duplicate.'
- CA-HU01-06: Los dos clientes aparecen con el mismo nombre en los selectores de apertura de cuentas e inicio de sesión, lo que dificulta diferenciarlos.
- CA-HU01-07: Hoy se cumple el primer resultado, pero el mensaje mostrado es de posible cliente duplicado.
- Otros escenarios relacionados: EXP-012 (base de RN-HU01-10).

## HU-02 — Abrir cuentas

**Historia de usuario**

Como administrador bancario, quiero abrir una cuenta para un cliente en la moneda que él elija, para que pueda empezar a depositar y retirar dinero.

**Reglas de negocio**

- **RN-HU02-01.** Para abrir una cuenta se debe seleccionar un cliente registrado y una moneda.
- **RN-HU02-02.** Las monedas disponibles son Dollar, Pound y Rupee.
- **RN-HU02-03.** Al abrir la cuenta, el sistema confirma la operación e informa el número de cuenta generado.
- **RN-HU02-04.** Los números de cuenta se asignan de forma consecutiva.
- **RN-HU02-05.** Toda cuenta nueva inicia con saldo cero en la moneda elegida.
- **RN-HU02-06.** Un cliente puede tener varias cuentas, incluso en monedas diferentes, y todas quedan asociadas a él en el listado de clientes.
- **RN-HU02-07.** Si no se selecciona el cliente o la moneda, la cuenta no se crea.

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU02-01 - Abrir una cuenta para un cliente registrado
    Given que existe el cliente "Ana" "QAPrueba" sin cuentas
    When el administrador abre una cuenta en Dollar para ese cliente
    Then se muestra una confirmación con el número de la cuenta creada
      And el listado de clientes muestra ese número de cuenta junto a Ana QAPrueba

Scenario: CA-HU02-02 - La cuenta nueva inicia con saldo cero en la moneda elegida
    Given que el administrador abrió una cuenta en Pound para el cliente "Ana" "QAPrueba"
    When el cliente ingresa y selecciona esa cuenta
    Then el saldo de la cuenta es 0
      And la moneda de la cuenta es Pound

Scenario: CA-HU02-03 - Abrir una segunda cuenta en otra moneda para el mismo cliente
    Given que el cliente "Ana" "QAPrueba" ya tiene una cuenta en Dollar
    When el administrador le abre otra cuenta en Pound
    Then se muestra una confirmación con el número de la nueva cuenta
      And el listado de clientes muestra las dos cuentas asociadas a Ana QAPrueba

Scenario: CA-HU02-04 - Impedir la apertura sin seleccionar el cliente
    Given que el administrador se encuentra en el formulario de apertura de cuentas
    When selecciona la moneda Dollar sin seleccionar un cliente
      And intenta abrir la cuenta
    Then no se crea ninguna cuenta
      And se indica que debe seleccionar un cliente

Scenario: CA-HU02-05 - Impedir la apertura sin seleccionar la moneda
    Given que el administrador se encuentra en el formulario de apertura de cuentas
    When selecciona al cliente "Ana" "QAPrueba" sin seleccionar una moneda
      And intenta abrir la cuenta
    Then no se crea ninguna cuenta
      And se indica que debe seleccionar una moneda
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU02-01 | Camino feliz | RN-HU02-01, RN-HU02-03 | EXP-016 | — | Cumple |
| CA-HU02-02 | Camino feliz | RN-HU02-05 | EXP-035, EXP-050 | — | Cumple |
| CA-HU02-03 | Alternativo | RN-HU02-04, RN-HU02-06 | EXP-017, EXP-018 | — | Cumple |
| CA-HU02-04 | Negativo | RN-HU02-01, RN-HU02-07 | EXP-019 | — | Cumple |
| CA-HU02-05 | Negativo | RN-HU02-01, RN-HU02-07 | EXP-020 | HAL-17 | Cumple |

Consideraciones:

- CA-HU02-01: Con los datos iniciales, la primera cuenta nueva es la 1016 y las siguientes son consecutivas.
- CA-HU02-02: Se observó en la preparación de EXP-035 (cuenta 1016 en Dollar) y en EXP-050 (cuenta 1017 en Pound).
- CA-HU02-05: El formulario conserva la selección anterior si se vuelve a pulsar la pestaña Open Account; el escenario debe partir de un formulario sin selección (HAL-17).
- Otros escenarios relacionados: EXP-002 (acceso al módulo del administrador).

## HU-03 — Consultar y buscar clientes

**Historia de usuario**

Como administrador bancario, quiero consultar el listado de clientes y buscar a uno en particular, para revisar rápidamente sus datos y las cuentas que tiene abiertas.

**Reglas de negocio**

- **RN-HU03-01.** El listado muestra, para cada cliente, su nombre, apellido, código postal y números de cuenta.
- **RN-HU03-02.** La búsqueda filtra el listado mientras se escribe y acepta coincidencias parciales en el nombre, el apellido, el código postal o el número de cuenta.
- **RN-HU03-03.** Si ningún cliente coincide con la búsqueda, el listado queda vacío.
- **RN-HU03-04.** El listado se puede ordenar por nombre, apellido o código postal; cada clic sobre el encabezado alterna el orden, empezando por el descendente.

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU03-01 - Consultar el listado de clientes
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador consulta el listado de clientes
    Then se muestran los cinco clientes con su nombre, apellido, código postal y cuentas
      And Harry Potter aparece con el código postal E725JB y las cuentas 1004, 1005 y 1006

Scenario: CA-HU03-02 - Buscar un cliente por su nombre
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador busca "Harry"
    Then el listado muestra únicamente a Harry Potter

Scenario: CA-HU03-03 - Buscar con una coincidencia parcial
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador busca "ott"
    Then el listado muestra únicamente a Harry Potter y a Neville Longbottom

Scenario: CA-HU03-04 - Buscar un cliente por su código postal
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador busca "E725JB"
    Then el listado muestra únicamente a Harry Potter

Scenario: CA-HU03-05 - Buscar un cliente que no existe
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador busca "ZZZNoExiste"
    Then el listado no muestra ningún cliente

Scenario: CA-HU03-06 - Ordenar el listado por nombre
    Given que el banco tiene sus cinco clientes iniciales
    When el administrador pulsa el encabezado del nombre
    Then los clientes se muestran en orden alfabético descendente: Ron, Neville, Hermoine, Harry y Albus
    When vuelve a pulsar el mismo encabezado
    Then los clientes se muestran en orden alfabético ascendente
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU03-01 | Camino feliz | RN-HU03-01 | EXP-021 | — | Cumple |
| CA-HU03-02 | Camino feliz | RN-HU03-02 | EXP-022, EXP-023 | — | Cumple |
| CA-HU03-03 | Alternativo | RN-HU03-02 | EXP-024 | — | Cumple |
| CA-HU03-04 | Alternativo | RN-HU03-02 | EXP-025 | — | Cumple |
| CA-HU03-05 | Negativo | RN-HU03-03 | EXP-026 | — | Cumple |
| CA-HU03-06 | Alternativo | RN-HU03-04 | EXP-027 | HAL-12 | Cumple |

Consideraciones:

- CA-HU03-01: Los datos iniciales se cargan en cada navegador nuevo.
- CA-HU03-02: EXP-023 confirmó la búsqueda por apellido con el mismo comportamiento.
- CA-HU03-04: EXP-025 también confirmó la búsqueda por número de cuenta (1016).
- CA-HU03-05: No aparece un mensaje de 'sin resultados'; la verificación debe hacerse sobre la cantidad de filas.
- CA-HU03-06: Al pulsar el encabezado la dirección del navegador cambia a '#', aunque el listado se mantiene (HAL-12).
- Otros escenarios relacionados: EXP-002 (acceso al módulo del administrador).

## HU-04 — Eliminar clientes

**Historia de usuario**

Como administrador bancario, quiero eliminar a un cliente, para que deje de figurar en el banco y ya no pueda ingresar a sus cuentas.

**Reglas de negocio**

- **RN-HU04-01.** Al eliminar un cliente, este desaparece del listado de clientes.
- **RN-HU04-02.** Un cliente eliminado deja de estar disponible para abrir cuentas y para iniciar sesión.
- **RN-HU04-03.** Antes de eliminar un cliente, el sistema debería pedir una confirmación. _Expectativa de calidad por confirmar (HAL-11). Hoy la eliminación es inmediata._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU04-01 - Eliminar un cliente
    Given que existe el cliente "Luis" "QAExplora"
    When el administrador elimina a ese cliente desde el listado
    Then Luis QAExplora ya no aparece en el listado de clientes

Scenario: CA-HU04-02 - Un cliente eliminado no puede iniciar sesión
    Given que el administrador eliminó al cliente "Luis" "QAExplora"
    When se consulta la lista de nombres para iniciar sesión como cliente
    Then Luis QAExplora no aparece en la lista
      And tampoco aparece entre los clientes disponibles para abrir cuentas
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU04-01 | Camino feliz | RN-HU04-01 | EXP-028 | HAL-11 | Cumple |
| CA-HU04-02 | Alternativo | RN-HU04-02 | EXP-028 | — | Cumple |

Consideraciones:

- CA-HU04-01: La eliminación se ejecuta sin confirmación previa (HAL-11).
- CA-HU04-02: En EXP-028 se verificó leyendo las opciones de ambos selectores.

## HU-05 — Ingresar como cliente

**Historia de usuario**

Como cliente, quiero ingresar a la banca seleccionando mi nombre, para consultar mis cuentas y hacer operaciones sobre ellas.

**Reglas de negocio**

- **RN-HU05-01.** Para ingresar, el cliente selecciona su nombre de la lista de clientes registrados.
- **RN-HU05-02.** La opción de ingresar solo está disponible cuando hay un nombre seleccionado.
- **RN-HU05-03.** Al ingresar, se muestra un saludo con el nombre del cliente y los datos de su primera cuenta: número, saldo y moneda.
- **RN-HU05-04.** Si el cliente no tiene cuentas, se le pide abrir una y no se ofrecen operaciones.

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU05-01 - Ingresar con un cliente registrado
    Given que Harry Potter es un cliente registrado con las cuentas 1004, 1005 y 1006
    When ingresa seleccionando su nombre
    Then se muestra el saludo "Welcome Harry Potter !!"
      And se muestra la cuenta 1004 con saldo 0 en Dollar

Scenario: CA-HU05-02 - No permitir el ingreso sin seleccionar un nombre
    Given que el cliente se encuentra en la pantalla de ingreso
    When no ha seleccionado ningún nombre
    Then no se ofrece la opción de ingresar
    When selecciona su nombre
    Then se habilita la opción de ingresar

Scenario: CA-HU05-03 - Ingresar con un cliente que no tiene cuentas
    Given que existe el cliente "Sofia" "QASinCuenta" sin cuentas
    When ingresa seleccionando su nombre
    Then se muestra el mensaje "Please open an account with us."
      And no se ofrecen las opciones de movimientos, depósito ni retiro
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU05-01 | Camino feliz | RN-HU05-01, RN-HU05-03 | EXP-029 | — | Cumple |
| CA-HU05-02 | Negativo | RN-HU05-02 | EXP-003 | — | Cumple |
| CA-HU05-03 | Alternativo | RN-HU05-04 | EXP-031 | — | Cumple |

Consideraciones:

- CA-HU05-01: Harry Potter es un cliente de los datos iniciales; este escenario solo consulta.
- CA-HU05-02: La restricción consiste en ocultar el botón Login; no hay mensaje de validación.
- Otros escenarios relacionados: EXP-001 (pantalla principal) y EXP-034 (el botón Home deja la sesión del cliente).

## HU-06 — Consultar el saldo de cada cuenta

**Historia de usuario**

Como cliente, quiero cambiar entre mis cuentas, para conocer el saldo y la moneda de cada una antes de hacer una operación.

**Reglas de negocio**

- **RN-HU06-01.** El cliente puede seleccionar cualquiera de sus cuentas.
- **RN-HU06-02.** Al cambiar de cuenta se actualizan el número, el saldo y la moneda que se muestran.
- **RN-HU06-03.** Los depósitos, los retiros y la consulta de movimientos se aplican solo a la cuenta seleccionada.

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU06-01 - Ver el saldo y la moneda de otra cuenta
    Given que Harry Potter ingresó a la banca
    When selecciona la cuenta 1005
    Then se muestra la cuenta 1005 con saldo 0 en Pound
    When selecciona la cuenta 1006
    Then se muestra la cuenta 1006 con saldo 0 en Rupee

Scenario: CA-HU06-02 - Una operación no afecta las otras cuentas del cliente
    Given que el cliente "Ana" "QAPrueba" tiene una cuenta en Dollar y otra en Pound, ambas con saldo 0
      And depositó 1000 en la cuenta en Dollar
    When selecciona la cuenta en Pound
    Then el saldo de esa cuenta sigue en 0
      And su historial de movimientos está vacío
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU06-01 | Camino feliz | RN-HU06-01, RN-HU06-02 | EXP-030 | — | Cumple |
| CA-HU06-02 | Alternativo | RN-HU06-03 | EXP-050 | — | Cumple |

Consideraciones:

- CA-HU06-02: En EXP-050 la cuenta 1017 (Pound) no tenía movimientos mientras la 1016 (Dollar) sí.

## HU-07 — Cerrar sesión

**Historia de usuario**

Como cliente, quiero cerrar mi sesión, para que nadie más pueda ver mi información ni hacer operaciones con mis cuentas desde el mismo equipo.

**Reglas de negocio**

- **RN-HU07-01.** Al cerrar sesión, el cliente vuelve a la pantalla de ingreso sin ningún nombre seleccionado.
- **RN-HU07-02.** La opción de cerrar sesión solo se muestra mientras el cliente está dentro de su cuenta.
- **RN-HU07-03.** Después de cerrar sesión, regresar con el botón Atrás del navegador no debe mostrar la información de la cuenta ni permitir operaciones. _Expectativa de calidad. Hoy la cuenta vuelve a mostrarse (HAL-02, confirmado). Si además se pueden ejecutar operaciones queda pendiente de verificación en el punto 2._
- **RN-HU07-04.** Las vistas de la cuenta no deben mostrarse a quien no ha iniciado sesión, aunque abra su dirección directamente. _Expectativa de calidad. Hoy se muestran con plantillas sin procesar y con la opción Logout (HAL-01)._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU07-01 - Cerrar la sesión
    Given que Harry Potter ingresó a la banca
    When cierra su sesión
    Then se muestra la pantalla de ingreso sin ningún nombre seleccionado
      And ya no se muestra la opción de cerrar sesión

Scenario: CA-HU07-02 - No mostrar la cuenta al regresar después de cerrar sesión
    Given que Harry Potter ingresó a la banca y luego cerró su sesión
    When regresa a la página anterior con el botón Atrás del navegador
    Then no se muestra la información de sus cuentas
      And se le pide ingresar nuevamente

Scenario: CA-HU07-03 - Impedir el acceso a la cuenta sin iniciar sesión
    Given que nadie ha iniciado sesión en el navegador
    When se abre directamente la dirección de la vista de cuenta
    Then no se muestra la vista de la cuenta
      And se redirige a la pantalla principal del banco
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU07-01 | Camino feliz | RN-HU07-01, RN-HU07-02 | EXP-032 | — | Cumple |
| CA-HU07-02 | Negativo | RN-HU07-03 | EXP-033 | HAL-02 | No cumple (HAL-02) |
| CA-HU07-03 | Negativo | RN-HU07-04 | EXP-005 | HAL-01 | No cumple (HAL-01) |

Consideraciones:

- CA-HU07-02: Hoy se vuelve a mostrar 'Welcome Harry Potter !!', sus cuentas y el saldo. La posibilidad de operar desde esa vista no quedó registrada en el punto 2.
- CA-HU07-03: Hoy se muestran textos sin procesar como '{{user}}' y '{{amount}}' y la opción Logout.
- Otros escenarios relacionados: EXP-034 (el botón Home lleva a la pantalla principal y oculta la opción Logout).

## HU-08 — Depositar dinero

**Historia de usuario**

Como cliente, quiero depositar dinero en una de mis cuentas, para aumentar el saldo que tengo disponible.

**Reglas de negocio**

- **RN-HU08-01.** El depósito se hace sobre la cuenta seleccionada y requiere un monto.
- **RN-HU08-02.** El monto debe ser mayor que cero.
- **RN-HU08-03.** Un depósito exitoso aumenta el saldo exactamente en el valor depositado y muestra el mensaje "Deposit Successful".
- **RN-HU08-04.** Cada depósito exitoso queda registrado en los movimientos como un crédito (Credit).
- **RN-HU08-05.** El monto solo admite números; no es posible escribir letras.
- **RN-HU08-06.** Cuando el monto es cero o negativo, el depósito no se realiza, el saldo no cambia y el sistema informa el motivo. _Expectativa de calidad. Hoy el depósito no se realiza, pero no se informa nada y se produce un error en la consola (HAL-03); si había un depósito exitoso previo, su mensaje sigue visible (HAL-04)._
- **RN-HU08-07.** Actualmente solo se aceptan montos enteros: los valores con decimales se rechazan. _Restricción observada por confirmar (HAL-15). No se convierte en criterio hasta que el equipo defina si los centavos deben aceptarse._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU08-01 - Depositar un monto válido
    Given que el cliente tiene una cuenta en Dollar con saldo 0
    When deposita 1000 en esa cuenta
    Then se muestra el mensaje "Deposit Successful"
      And el saldo de la cuenta pasa a 1000

Scenario: CA-HU08-02 - Acumular varios depósitos seguidos
    Given que el cliente tiene una cuenta en Dollar con saldo 1000
    When deposita 500
      And luego deposita 250
    Then el saldo de la cuenta pasa a 1750

Scenario: CA-HU08-03 - Impedir un depósito sin monto
    Given que el cliente tiene una cuenta en Dollar con saldo 1750
    When intenta depositar sin escribir un monto
    Then se indica que el monto es obligatorio
      And el saldo de la cuenta sigue en 1750

Scenario: CA-HU08-04 - Rechazar un depósito de cero
    Given que el cliente tiene una cuenta en Dollar con saldo 1750
    When intenta depositar 0
    Then el saldo de la cuenta sigue en 1750
      And se informa que el monto debe ser mayor que cero
      But no se muestra el mensaje "Deposit Successful"

Scenario: CA-HU08-05 - Rechazar un depósito negativo
    Given que el cliente tiene una cuenta en Dollar con saldo 1750
    When intenta depositar -100
    Then el saldo de la cuenta sigue en 1750
      And se informa que el monto debe ser mayor que cero
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU08-01 | Camino feliz | RN-HU08-01, RN-HU08-03 | EXP-035 | — | Cumple |
| CA-HU08-02 | Alternativo | RN-HU08-03 | EXP-036 | — | Cumple |
| CA-HU08-03 | Negativo | RN-HU08-01 | EXP-039 | — | Cumple |
| CA-HU08-04 | Negativo | RN-HU08-02, RN-HU08-06 | EXP-037 | HAL-03, HAL-04 | No cumple (HAL-03, HAL-04) |
| CA-HU08-05 | Negativo | RN-HU08-02, RN-HU08-06 | EXP-038 | HAL-03 | No cumple (HAL-03) |

Consideraciones:

- CA-HU08-04: Hoy se cumple el saldo, pero no hay mensaje de error; si antes hubo un depósito exitoso sin cambiar de pestaña, sigue visible 'Deposit Successful'.
- CA-HU08-05: Hoy se cumple el saldo, pero no aparece ningún mensaje.
- Otros escenarios relacionados: EXP-040 (decimales, RN-HU08-07) y EXP-041 (letras y notación '1e2', RN-HU08-05).

## HU-09 — Retirar dinero

**Historia de usuario**

Como cliente, quiero retirar dinero de una de mis cuentas, para usar mi saldo cuando lo necesite.

**Reglas de negocio**

- **RN-HU09-01.** El retiro se hace sobre la cuenta seleccionada y requiere un monto.
- **RN-HU09-02.** El monto debe ser mayor que cero y no puede superar el saldo disponible.
- **RN-HU09-03.** Un retiro exitoso disminuye el saldo exactamente en el valor retirado y muestra el mensaje "Transaction successful".
- **RN-HU09-04.** Se puede retirar la totalidad del saldo; en ese caso la cuenta queda en cero.
- **RN-HU09-05.** Si el monto supera el saldo, el retiro no se realiza, el saldo no cambia y se muestra el mensaje "Transaction Failed. You can not withdraw amount more than the balance."
- **RN-HU09-06.** Cada retiro exitoso queda registrado en los movimientos como un débito (Debit).
- **RN-HU09-07.** El monto solo admite números enteros; no es posible escribir letras. _Los decimales se rechazan (restricción por confirmar, HAL-15)._
- **RN-HU09-08.** Cuando el monto es cero o negativo, el retiro no se realiza, el saldo no cambia y el sistema informa el motivo. _Expectativa de calidad. Hoy el retiro no se realiza, pero no se informa nada y se produce un error en la consola (HAL-05)._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU09-01 - Retirar un monto menor al saldo
    Given que el cliente tiene una cuenta en Dollar con saldo 1000
    When retira 300 de esa cuenta
    Then se muestra el mensaje "Transaction successful"
      And el saldo de la cuenta pasa a 700

Scenario: CA-HU09-02 - Retirar la totalidad del saldo
    Given que el cliente tiene una cuenta en Dollar con saldo 1550
    When retira 1550
    Then se muestra el mensaje "Transaction successful"
      And el saldo de la cuenta pasa a 0

Scenario: CA-HU09-03 - Rechazar un retiro mayor al saldo
    Given que el cliente tiene una cuenta en Dollar con saldo 1550
    When intenta retirar 2550
    Then se muestra el mensaje "Transaction Failed. You can not withdraw amount more than the balance."
      And el saldo de la cuenta sigue en 1550

Scenario: CA-HU09-04 - Impedir un retiro sin monto
    Given que el cliente tiene una cuenta en Dollar con saldo 1550
    When intenta retirar sin escribir un monto
    Then se indica que el monto es obligatorio
      And el saldo de la cuenta sigue en 1550

Scenario: CA-HU09-05 - Rechazar un retiro de cero
    Given que el cliente tiene una cuenta en Dollar con saldo 1550
    When intenta retirar 0
    Then el saldo de la cuenta sigue en 1550
      And se informa que el monto debe ser mayor que cero

Scenario: CA-HU09-06 - Rechazar un retiro negativo
    Given que el cliente tiene una cuenta en Dollar con saldo 1550
    When intenta retirar -50
    Then el saldo de la cuenta sigue en 1550
      And se informa que el monto debe ser mayor que cero
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU09-01 | Camino feliz | RN-HU09-01, RN-HU09-03 | EXP-042 | HAL-17 | Cumple |
| CA-HU09-02 | Alternativo | RN-HU09-04 | EXP-044 | — | Cumple |
| CA-HU09-03 | Negativo | RN-HU09-02, RN-HU09-05 | EXP-043, EXP-044 | — | Cumple |
| CA-HU09-04 | Negativo | RN-HU09-01 | EXP-047 | — | Cumple |
| CA-HU09-05 | Negativo | RN-HU09-02, RN-HU09-08 | EXP-045 | HAL-05 | No cumple (HAL-05) |
| CA-HU09-06 | Negativo | RN-HU09-02, RN-HU09-08 | EXP-046 | HAL-05 | No cumple (HAL-05) |

Consideraciones:

- CA-HU09-01: El formulario de retiro carga después del clic; el monto debe escribirse cuando ya está visible (HAL-17).
- CA-HU09-03: EXP-044 confirmó el mismo resultado al intentar retirar 1 con saldo 0.
- CA-HU09-05: Hoy se cumple el saldo, pero no aparece ningún mensaje.
- CA-HU09-06: Se comprobó que el retiro negativo no aumenta el saldo; falta el mensaje.
- Otros escenarios relacionados: EXP-048 (decimales y letras, RN-HU09-07).

## HU-10 — Consultar movimientos

**Historia de usuario**

Como cliente, quiero ver el historial de movimientos de mi cuenta, para revisar los depósitos y retiros que he hecho.

**Reglas de negocio**

- **RN-HU10-01.** El historial muestra cada movimiento con su fecha y hora, su monto y su tipo: Credit para depósitos y Debit para retiros.
- **RN-HU10-02.** Solo se registran las operaciones exitosas; los depósitos y retiros rechazados no aparecen en el historial.
- **RN-HU10-03.** Una cuenta sin movimientos muestra el historial vacío y no ofrece filtros ni la opción de reiniciar.
- **RN-HU10-04.** Los movimientos se pueden ordenar por fecha y hora; cada clic sobre el encabezado alterna el orden, empezando por el descendente.
- **RN-HU10-05.** Desde el historial, el cliente puede volver a la vista de su cuenta y, cuando hay muchos movimientos, desplazarse con los controles de la parte inferior.

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU10-01 - Ver los depósitos y retiros en el historial
    Given que el cliente depositó 1000 en su cuenta en Dollar
      And luego retiró 300 de la misma cuenta
    When consulta los movimientos de esa cuenta
    Then se muestra un movimiento de 1000 de tipo Credit
      And se muestra un movimiento de 300 de tipo Debit
      And cada movimiento tiene su fecha y hora

Scenario: CA-HU10-02 - Un retiro rechazado no aparece en el historial
    Given que el cliente tiene una cuenta en Dollar con un depósito de 1000
      And intentó retirar 2000 y el retiro fue rechazado
    When consulta los movimientos de esa cuenta
    Then solo se muestra el depósito de 1000
      And no aparece ningún movimiento de tipo Debit

Scenario: CA-HU10-03 - Consultar una cuenta sin movimientos
    Given que el cliente tiene una cuenta recién abierta
    When consulta los movimientos de esa cuenta
    Then el historial no muestra ningún movimiento
      And no se ofrecen los filtros de fecha ni la opción de reiniciar

Scenario: CA-HU10-04 - Ordenar los movimientos por fecha
    Given que el cliente tiene varios movimientos en su cuenta
    When pulsa el encabezado de fecha y hora
    Then el movimiento más reciente aparece primero
    When vuelve a pulsar el mismo encabezado
    Then el movimiento más antiguo aparece primero

Scenario: CA-HU10-05 - Recorrer un historial extenso y volver a la cuenta
    Given que Hermoine Granger consulta los movimientos de su cuenta 1001
    When usa el control para avanzar en el historial
    Then la tabla se desplaza y aparecen los controles para retroceder y volver al inicio
    When pulsa la opción para volver
    Then se muestra nuevamente la cuenta 1001 con su saldo
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU10-01 | Camino feliz | RN-HU10-01, RN-HU08-04, RN-HU09-06 | EXP-049 | — | Cumple |
| CA-HU10-02 | Negativo | RN-HU10-02 | EXP-043, EXP-049 | — | Cumple |
| CA-HU10-03 | Alternativo | RN-HU10-03 | EXP-050 | — | Cumple |
| CA-HU10-04 | Alternativo | RN-HU10-04 | EXP-053 | HAL-12 | Cumple |
| CA-HU10-05 | Alternativo | RN-HU10-05 | EXP-054 | — | Cumple |

Consideraciones:

- CA-HU10-02: En EXP-049 las operaciones rechazadas de EXP-037 a EXP-046 no generaron movimientos.
- CA-HU10-04: Al pulsar el encabezado la dirección del navegador cambia a '#' (HAL-12).
- CA-HU10-05: La cuenta 1001 tiene 392 movimientos precargados de 2015; el escenario solo consulta.

## HU-11 — Filtrar movimientos por fecha

**Historia de usuario**

Como cliente, quiero filtrar mis movimientos por un rango de fecha y hora, para revisar solo las operaciones de un período específico.

**Reglas de negocio**

- **RN-HU11-01.** El filtro se ofrece solo cuando la cuenta tiene movimientos.
- **RN-HU11-02.** Al abrir el historial, el rango inicial va desde el primer hasta el último movimiento de la cuenta.
- **RN-HU11-03.** Al definir un rango, solo se muestran los movimientos cuya fecha y hora están dentro de él.
- **RN-HU11-04.** Si no hay movimientos dentro del rango, el historial queda vacío.
- **RN-HU11-05.** Si la fecha final elegida es posterior al momento en que se abrió el historial, el sistema debe aceptar el rango o explicar por qué no lo acepta. _Expectativa de calidad. Hoy el campo queda inválido y la tabla se vacía sin ningún aviso (HAL-09)._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU11-01 - Filtrar los movimientos de un período
    Given que Hermoine Granger consulta los movimientos de su cuenta 1001
    When filtra desde el 1 de enero de 2015 a las 00:00 hasta el 3 de enero de 2015 a las 23:59
    Then se muestran 6 movimientos
      And todos tienen fecha entre el 1 y el 3 de enero de 2015

Scenario: CA-HU11-02 - Filtrar un período sin movimientos
    Given que Hermoine Granger consulta los movimientos de su cuenta 1001
    When filtra desde el 1 de enero de 2015 a la 01:00 hasta el mismo día a las 23:00
    Then el historial no muestra ningún movimiento

Scenario: CA-HU11-03 - Filtrar los movimientos recientes de la cuenta
    Given que el cliente hizo dos depósitos y, un minuto después, un retiro de 200
    When filtra desde el minuto en que hizo el retiro hasta el minuto actual
    Then solo se muestra el retiro de 200 de tipo Debit

Scenario: CA-HU11-04 - Avisar cuando la fecha final no se puede usar
    Given que el cliente consulta el historial de una cuenta con movimientos
    When define una fecha final 30 minutos posterior al momento en que abrió el historial
    Then se muestran los movimientos del rango o se informa que la fecha final no es válida
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU11-01 | Camino feliz | RN-HU11-03 | EXP-054 | — | Cumple |
| CA-HU11-02 | Negativo | RN-HU11-04 | EXP-052 | — | Cumple |
| CA-HU11-03 | Alternativo | RN-HU11-02, RN-HU11-03 | EXP-051 | — | Cumple |
| CA-HU11-04 | Negativo | RN-HU11-05 | EXP-051 | HAL-09 | No cumple (HAL-09) |

Consideraciones:

- CA-HU11-01: Usa datos precargados que no cambian entre ejecuciones.
- CA-HU11-02: Los movimientos precargados están registrados a las 00:00 de cada día.
- CA-HU11-03: Depende de la hora real y de que las operaciones queden en minutos distintos; ver la sección F.I.R.S.T.
- CA-HU11-04: Hoy la tabla queda vacía sin ningún mensaje.

## HU-12 — Reiniciar el historial de una cuenta

**Historia de usuario**

Como cliente, quiero reiniciar el historial de una de mis cuentas, para dejarla sin movimientos y empezar un registro nuevo.

**Reglas de negocio**

- **RN-HU12-01.** El reinicio elimina todos los movimientos de la cuenta seleccionada.
- **RN-HU12-02.** La opción de reiniciar solo se ofrece cuando la cuenta tiene movimientos.
- **RN-HU12-03.** Después del reinicio, el saldo de la cuenta queda en cero. _Comportamiento observado por confirmar (HAL-10). No se convierte en criterio hasta que el equipo defina si es el comportamiento esperado._
- **RN-HU12-04.** Antes de reiniciar, el sistema debería pedir una confirmación, porque la acción no se puede deshacer. _Expectativa de calidad por confirmar (HAL-10)._

**Criterios de aceptación — Escenarios Gherkin**

```gherkin
Scenario: CA-HU12-01 - Reiniciar el historial de una cuenta con movimientos
    Given que el cliente tiene una cuenta de prueba con varios movimientos
    When reinicia el historial de esa cuenta
    Then el historial no muestra ningún movimiento
```

**Trazabilidad de la historia**

| CA | Tipo | Reglas | EXP | HAL | Estado actual |
|---|---|---|---|---|---|
| CA-HU12-01 | Camino feliz | RN-HU12-01 | EXP-055 | HAL-10 | Cumple |

Consideraciones:

- CA-HU12-01: Ejecutar solo con cuentas de prueba. En EXP-055 el saldo también quedó en 0 y no se pidió confirmación (HAL-10).

## Matriz consolidada de trazabilidad

| HU | Historia de usuario | RN | CA | Tipo | EXP | HAL | Observaciones |
|---|---|---|---|---|---|---|---|
| HU-01 | Registrar clientes | RN-HU01-01, RN-HU01-03 | CA-HU01-01 - Registrar un cliente con datos completos | Camino feliz | EXP-007, EXP-008 | — | Cumple. En un navegador con los datos iniciales, el primer cliente nuevo recibe el identificador 6 y el siguiente el 7. La confirmación es una alerta del navegador. |
| HU-01 | Registrar clientes | RN-HU01-01, RN-HU01-02 | CA-HU01-02 - Impedir el registro sin ningún dato | Negativo | EXP-009 | — | Cumple. La indicación es la validación nativa del navegador; su texto depende del idioma del navegador. |
| HU-01 | Registrar clientes | RN-HU01-01, RN-HU01-02 | CA-HU01-03 - Impedir el registro sin el código postal | Negativo | EXP-010 | — | Cumple. EXP-010 mostró el mismo comportamiento cuando falta el apellido. |
| HU-01 | Registrar clientes | RN-HU01-05 | CA-HU01-04 - Registrar un cliente con tildes, guiones y apóstrofes en el nombre | Alternativo | EXP-011 | — | Cumple. En EXP-011 también se aceptó un código postal compuesto solo por símbolos (ver HAL-08). |
| HU-01 | Registrar clientes | RN-HU01-06 | CA-HU01-05 - Rechazar un cliente duplicado | Negativo | EXP-014 | — | Cumple. Mensaje observado: 'Please check the details. Customer may be duplicate.' |
| HU-01 | Registrar clientes | RN-HU01-07 | CA-HU01-06 - Registrar un cliente con el mismo nombre y otro código postal | Alternativo | EXP-015 | — | Cumple. Los dos clientes aparecen con el mismo nombre en los selectores de apertura de cuentas e inicio de sesión, lo que dificulta diferenciarlos. |
| HU-01 | Registrar clientes | RN-HU01-02, RN-HU01-08 | CA-HU01-07 - Impedir el registro con datos que solo contienen espacios | Negativo | EXP-013 | HAL-06 | No cumple (HAL-06). Hoy se cumple el primer resultado, pero el mensaje mostrado es de posible cliente duplicado. |
| HU-02 | Abrir cuentas | RN-HU02-01, RN-HU02-03 | CA-HU02-01 - Abrir una cuenta para un cliente registrado | Camino feliz | EXP-016 | — | Cumple. Con los datos iniciales, la primera cuenta nueva es la 1016 y las siguientes son consecutivas. |
| HU-02 | Abrir cuentas | RN-HU02-05 | CA-HU02-02 - La cuenta nueva inicia con saldo cero en la moneda elegida | Camino feliz | EXP-035, EXP-050 | — | Cumple. Se observó en la preparación de EXP-035 (cuenta 1016 en Dollar) y en EXP-050 (cuenta 1017 en Pound). |
| HU-02 | Abrir cuentas | RN-HU02-04, RN-HU02-06 | CA-HU02-03 - Abrir una segunda cuenta en otra moneda para el mismo cliente | Alternativo | EXP-017, EXP-018 | — | Cumple. |
| HU-02 | Abrir cuentas | RN-HU02-01, RN-HU02-07 | CA-HU02-04 - Impedir la apertura sin seleccionar el cliente | Negativo | EXP-019 | — | Cumple. |
| HU-02 | Abrir cuentas | RN-HU02-01, RN-HU02-07 | CA-HU02-05 - Impedir la apertura sin seleccionar la moneda | Negativo | EXP-020 | HAL-17 | Cumple. El formulario conserva la selección anterior si se vuelve a pulsar la pestaña Open Account; el escenario debe partir de un formulario sin selección (HAL-17). |
| HU-03 | Consultar y buscar clientes | RN-HU03-01 | CA-HU03-01 - Consultar el listado de clientes | Camino feliz | EXP-021 | — | Cumple. Los datos iniciales se cargan en cada navegador nuevo. |
| HU-03 | Consultar y buscar clientes | RN-HU03-02 | CA-HU03-02 - Buscar un cliente por su nombre | Camino feliz | EXP-022, EXP-023 | — | Cumple. EXP-023 confirmó la búsqueda por apellido con el mismo comportamiento. |
| HU-03 | Consultar y buscar clientes | RN-HU03-02 | CA-HU03-03 - Buscar con una coincidencia parcial | Alternativo | EXP-024 | — | Cumple. |
| HU-03 | Consultar y buscar clientes | RN-HU03-02 | CA-HU03-04 - Buscar un cliente por su código postal | Alternativo | EXP-025 | — | Cumple. EXP-025 también confirmó la búsqueda por número de cuenta (1016). |
| HU-03 | Consultar y buscar clientes | RN-HU03-03 | CA-HU03-05 - Buscar un cliente que no existe | Negativo | EXP-026 | — | Cumple. No aparece un mensaje de 'sin resultados'; la verificación debe hacerse sobre la cantidad de filas. |
| HU-03 | Consultar y buscar clientes | RN-HU03-04 | CA-HU03-06 - Ordenar el listado por nombre | Alternativo | EXP-027 | HAL-12 | Cumple. Al pulsar el encabezado la dirección del navegador cambia a '#', aunque el listado se mantiene (HAL-12). |
| HU-04 | Eliminar clientes | RN-HU04-01 | CA-HU04-01 - Eliminar un cliente | Camino feliz | EXP-028 | HAL-11 | Cumple. La eliminación se ejecuta sin confirmación previa (HAL-11). |
| HU-04 | Eliminar clientes | RN-HU04-02 | CA-HU04-02 - Un cliente eliminado no puede iniciar sesión | Alternativo | EXP-028 | — | Cumple. En EXP-028 se verificó leyendo las opciones de ambos selectores. |
| HU-05 | Ingresar como cliente | RN-HU05-01, RN-HU05-03 | CA-HU05-01 - Ingresar con un cliente registrado | Camino feliz | EXP-029 | — | Cumple. Harry Potter es un cliente de los datos iniciales; este escenario solo consulta. |
| HU-05 | Ingresar como cliente | RN-HU05-02 | CA-HU05-02 - No permitir el ingreso sin seleccionar un nombre | Negativo | EXP-003 | — | Cumple. La restricción consiste en ocultar el botón Login; no hay mensaje de validación. |
| HU-05 | Ingresar como cliente | RN-HU05-04 | CA-HU05-03 - Ingresar con un cliente que no tiene cuentas | Alternativo | EXP-031 | — | Cumple. |
| HU-06 | Consultar el saldo de cada cuenta | RN-HU06-01, RN-HU06-02 | CA-HU06-01 - Ver el saldo y la moneda de otra cuenta | Camino feliz | EXP-030 | — | Cumple. |
| HU-06 | Consultar el saldo de cada cuenta | RN-HU06-03 | CA-HU06-02 - Una operación no afecta las otras cuentas del cliente | Alternativo | EXP-050 | — | Cumple. En EXP-050 la cuenta 1017 (Pound) no tenía movimientos mientras la 1016 (Dollar) sí. |
| HU-07 | Cerrar sesión | RN-HU07-01, RN-HU07-02 | CA-HU07-01 - Cerrar la sesión | Camino feliz | EXP-032 | — | Cumple. |
| HU-07 | Cerrar sesión | RN-HU07-03 | CA-HU07-02 - No mostrar la cuenta al regresar después de cerrar sesión | Negativo | EXP-033 | HAL-02 | No cumple (HAL-02). Hoy se vuelve a mostrar 'Welcome Harry Potter !!', sus cuentas y el saldo. La posibilidad de operar desde esa vista no quedó registrada en el punto 2. |
| HU-07 | Cerrar sesión | RN-HU07-04 | CA-HU07-03 - Impedir el acceso a la cuenta sin iniciar sesión | Negativo | EXP-005 | HAL-01 | No cumple (HAL-01). Hoy se muestran textos sin procesar como '{{user}}' y '{{amount}}' y la opción Logout. |
| HU-08 | Depositar dinero | RN-HU08-01, RN-HU08-03 | CA-HU08-01 - Depositar un monto válido | Camino feliz | EXP-035 | — | Cumple. |
| HU-08 | Depositar dinero | RN-HU08-03 | CA-HU08-02 - Acumular varios depósitos seguidos | Alternativo | EXP-036 | — | Cumple. |
| HU-08 | Depositar dinero | RN-HU08-01 | CA-HU08-03 - Impedir un depósito sin monto | Negativo | EXP-039 | — | Cumple. |
| HU-08 | Depositar dinero | RN-HU08-02, RN-HU08-06 | CA-HU08-04 - Rechazar un depósito de cero | Negativo | EXP-037 | HAL-03, HAL-04 | No cumple (HAL-03, HAL-04). Hoy se cumple el saldo, pero no hay mensaje de error; si antes hubo un depósito exitoso sin cambiar de pestaña, sigue visible 'Deposit Successful'. |
| HU-08 | Depositar dinero | RN-HU08-02, RN-HU08-06 | CA-HU08-05 - Rechazar un depósito negativo | Negativo | EXP-038 | HAL-03 | No cumple (HAL-03). Hoy se cumple el saldo, pero no aparece ningún mensaje. |
| HU-09 | Retirar dinero | RN-HU09-01, RN-HU09-03 | CA-HU09-01 - Retirar un monto menor al saldo | Camino feliz | EXP-042 | HAL-17 | Cumple. El formulario de retiro carga después del clic; el monto debe escribirse cuando ya está visible (HAL-17). |
| HU-09 | Retirar dinero | RN-HU09-04 | CA-HU09-02 - Retirar la totalidad del saldo | Alternativo | EXP-044 | — | Cumple. |
| HU-09 | Retirar dinero | RN-HU09-02, RN-HU09-05 | CA-HU09-03 - Rechazar un retiro mayor al saldo | Negativo | EXP-043, EXP-044 | — | Cumple. EXP-044 confirmó el mismo resultado al intentar retirar 1 con saldo 0. |
| HU-09 | Retirar dinero | RN-HU09-01 | CA-HU09-04 - Impedir un retiro sin monto | Negativo | EXP-047 | — | Cumple. |
| HU-09 | Retirar dinero | RN-HU09-02, RN-HU09-08 | CA-HU09-05 - Rechazar un retiro de cero | Negativo | EXP-045 | HAL-05 | No cumple (HAL-05). Hoy se cumple el saldo, pero no aparece ningún mensaje. |
| HU-09 | Retirar dinero | RN-HU09-02, RN-HU09-08 | CA-HU09-06 - Rechazar un retiro negativo | Negativo | EXP-046 | HAL-05 | No cumple (HAL-05). Se comprobó que el retiro negativo no aumenta el saldo; falta el mensaje. |
| HU-10 | Consultar movimientos | RN-HU10-01, RN-HU08-04, RN-HU09-06 | CA-HU10-01 - Ver los depósitos y retiros en el historial | Camino feliz | EXP-049 | — | Cumple. |
| HU-10 | Consultar movimientos | RN-HU10-02 | CA-HU10-02 - Un retiro rechazado no aparece en el historial | Negativo | EXP-043, EXP-049 | — | Cumple. En EXP-049 las operaciones rechazadas de EXP-037 a EXP-046 no generaron movimientos. |
| HU-10 | Consultar movimientos | RN-HU10-03 | CA-HU10-03 - Consultar una cuenta sin movimientos | Alternativo | EXP-050 | — | Cumple. |
| HU-10 | Consultar movimientos | RN-HU10-04 | CA-HU10-04 - Ordenar los movimientos por fecha | Alternativo | EXP-053 | HAL-12 | Cumple. Al pulsar el encabezado la dirección del navegador cambia a '#' (HAL-12). |
| HU-10 | Consultar movimientos | RN-HU10-05 | CA-HU10-05 - Recorrer un historial extenso y volver a la cuenta | Alternativo | EXP-054 | — | Cumple. La cuenta 1001 tiene 392 movimientos precargados de 2015; el escenario solo consulta. |
| HU-11 | Filtrar movimientos por fecha | RN-HU11-03 | CA-HU11-01 - Filtrar los movimientos de un período | Camino feliz | EXP-054 | — | Cumple. Usa datos precargados que no cambian entre ejecuciones. |
| HU-11 | Filtrar movimientos por fecha | RN-HU11-04 | CA-HU11-02 - Filtrar un período sin movimientos | Negativo | EXP-052 | — | Cumple. Los movimientos precargados están registrados a las 00:00 de cada día. |
| HU-11 | Filtrar movimientos por fecha | RN-HU11-02, RN-HU11-03 | CA-HU11-03 - Filtrar los movimientos recientes de la cuenta | Alternativo | EXP-051 | — | Cumple. Depende de la hora real y de que las operaciones queden en minutos distintos; ver la sección F.I.R.S.T. |
| HU-11 | Filtrar movimientos por fecha | RN-HU11-05 | CA-HU11-04 - Avisar cuando la fecha final no se puede usar | Negativo | EXP-051 | HAL-09 | No cumple (HAL-09). Hoy la tabla queda vacía sin ningún mensaje. |
| HU-12 | Reiniciar el historial de una cuenta | RN-HU12-01 | CA-HU12-01 - Reiniciar el historial de una cuenta con movimientos | Camino feliz | EXP-055 | HAL-10 | Cumple. Ejecutar solo con cuentas de prueba. En EXP-055 el saldo también quedó en 0 y no se pidió confirmación (HAL-10). |

### Hallazgos considerados

| HAL | Hallazgo del punto 2 | Dónde se consideró |
|---|---|---|
| HAL-01 | Rutas internas accesibles sin sesión | HU-07 (RN-HU07-04, CA-HU07-03) |
| HAL-02 | Regreso a la cuenta con Atrás después del Logout | HU-07 (RN-HU07-03, CA-HU07-02) |
| HAL-03 | Depósitos de 0 o negativos sin mensaje | HU-08 (RN-HU08-06, CA-HU08-04, CA-HU08-05) |
| HAL-04 | Mensaje de éxito anterior visible tras un depósito rechazado | HU-08 (RN-HU08-06, CA-HU08-04) |
| HAL-05 | Retiros de 0 o negativos sin mensaje | HU-09 (RN-HU09-08, CA-HU09-05, CA-HU09-06) |
| HAL-06 | Mensaje de duplicado ante campos con espacios | HU-01 (RN-HU01-08, CA-HU01-07) |
| HAL-07 | Duplicados sensibles a mayúsculas | HU-01 (RN-HU01-09, sin criterio hasta confirmar) |
| HAL-08 | Sin validación de formato en nombre y código postal | HU-01 (RN-HU01-10, sin criterio hasta confirmar) |
| HAL-09 | Fecha final del filtro posterior a la apertura | HU-11 (RN-HU11-05, CA-HU11-04) |
| HAL-10 | Reset deja el saldo en 0 y no pide confirmación | HU-12 (RN-HU12-03, RN-HU12-04) |
| HAL-11 | Eliminación de clientes sin confirmación | HU-04 (RN-HU04-03) |
| HAL-12 | Los encabezados ordenables cambian la dirección a '#' | HU-03 (CA-HU03-06), HU-10 (CA-HU10-04) |
| HAL-13 | No existen transferencias | Alcance y limitaciones (HU-F01 propuesta) |
| HAL-14 | Texto 'Withdrawl' y errores en datos precargados | Consideraciones para Serenity |
| HAL-15 | Montos solo enteros y aceptación de '1e2' | HU-08 (RN-HU08-07), HU-09 (RN-HU09-07) |
| HAL-16 | Administrador sin credenciales ni Logout | Alcance y consideraciones para Serenity |
| HAL-17 | Sincronización de formularios | HU-02 (CA-HU02-05), HU-09 (CA-HU09-01), consideraciones para Serenity |

## Aplicación del principio F.I.R.S.T.

| Principio | Interpretación | Aplicación en GlobalSQA | Ejemplo concreto | Riesgos o consideraciones |
|---|---|---|---|---|
| F — Fast | Cada prueba se ejecuta en poco tiempo y verifica una sola cosa. | Escenarios cortos que preparan sus datos por el camino más directo y no recorren pantallas que no aportan al resultado. | CA-HU08-01 solo prepara un cliente con una cuenta, hace el depósito y revisa el mensaje y el saldo. | Registrar el cliente y abrir la cuenta por la interfaz se repite en muchos escenarios; conviene reutilizarlo como una sola tarea. Las esperas fijas hacen lentas las pruebas; es mejor esperar a que aparezca el elemento. |
| I — Independent | Ningún escenario depende de que otro se haya ejecutado antes. | Cada escenario prepara en su Given los clientes, cuentas y saldos que necesita, en un navegador nuevo. | CA-HU09-03 deja su cuenta con saldo 1550 mediante un depósito propio, en lugar de usar el saldo que dejó CA-HU09-01. | Si varios escenarios comparten el mismo navegador, los identificadores de cliente y de cuenta cambian y los saldos se mezclan. |
| R — Repeatable | El resultado es el mismo en cada ejecución. | La aplicación guarda los datos en el navegador. Al iniciar cada escenario con un navegador limpio, el estado inicial es siempre el mismo: 5 clientes, cuentas 1001 a 1015 y movimientos de 2015 en la cuenta 1001. | CA-HU11-01 filtra movimientos precargados de 2015, por lo que siempre devuelve 6 movimientos. | CA-HU11-03 y CA-HU11-04 dependen de la hora real. Los textos de las validaciones nativas dependen del idioma del navegador. |
| S — Self-validating | La prueba decide por sí sola si pasa o falla, sin revisión manual. | Cada Then tiene un valor comparable: un saldo, un mensaje exacto, una cantidad de filas o un tipo de movimiento. | CA-HU08-01 verifica el mensaje "Deposit Successful" y que el saldo pase a 1000. | Los mensajes del administrador son alertas del navegador y deben leerse antes de aceptarlas. Los criterios marcados 'No cumple' fallarán mientras el defecto exista. |
| T — Timely | Las pruebas se escriben en el momento oportuno del proceso. | La aplicación ya existe, así que no se trata de escribir pruebas antes del producto. Los criterios se definieron después de explorar y antes de automatizar, de modo que la automatización parte de requisitos acordados. | CA-HU07-02 describe lo que debe pasar al regresar después de cerrar sesión; al automatizarlo, la prueba dejará en evidencia HAL-02. | Si la aplicación o el enunciado cambian, los criterios y la trazabilidad deben actualizarse antes de modificar las pruebas. |

## Consideraciones para la implementación con Serenity BDD

**Escenarios independientes**

- Abrir un navegador limpio en cada escenario. Como todo se guarda en el almacenamiento del navegador, así se evita que un escenario herede clientes, cuentas o saldos de otro.

**Datos de prueba**

- Usar clientes identificables con el prefijo QA (Ana QAPrueba, Luis QAExplora, Sofia QASinCuenta) y los montos de los criterios.
- En un navegador limpio, el primer cliente nuevo recibe el identificador 6 y la primera cuenta nueva el número 1016. Es mejor leer el valor informado en la confirmación que fijarlo en la prueba.

**Estado inicial**

- Los clientes precargados (Harry Potter, Hermoine Granger y los demás) solo se usan en escenarios de consulta.
- Los depósitos, retiros y el reinicio del historial se ejecutan sobre cuentas creadas por el mismo escenario.

**Validaciones**

- Las confirmaciones del administrador son alertas del navegador: hay que leer su texto y luego aceptarlas.
- Los campos obligatorios usan la validación nativa del navegador. Conviene verificar que el campo quedó inválido en lugar de buscar el texto, que cambia según el idioma.
- Los mensajes de depósito y retiro aparecen dentro de la página, en inglés.

**Manejo de cuentas**

- Al ingresar, el cliente queda en su primera cuenta. Si el escenario usa otra, debe seleccionarla de forma explícita antes de operar.
- Leer el saldo antes y después de cada operación para comprobar el cálculo.

**Reutilización**

- Las acciones de registrar un cliente, abrir una cuenta, ingresar como cliente, seleccionar una cuenta, depositar y retirar se repiten en casi todos los criterios y conviene implementarlas una sola vez.

**Dependencias entre operaciones**

- Para probar retiros, movimientos o el reinicio, el saldo y los movimientos necesarios se crean en el Given del mismo escenario, normalmente con un depósito.
- El formulario de retiro carga después del clic. Si se escribe el monto antes de que aparezca, el valor se pierde (HAL-17).

**Hallazgos que afectan la automatización**

- Los criterios marcados 'No cumple' (HAL-01 a HAL-06 y HAL-09) deben etiquetarse para identificarlos como defectos conocidos. No se deben ajustar para que pasen.
- La pestaña de retiro se llama 'Withdrawl' (HAL-14); es preferible no ubicarla por su texto.
- Al ordenar listados, la dirección cambia a '#' (HAL-12). No se debe usar la URL para confirmar en qué vista se está.
- El formulario de apertura de cuentas conserva la selección anterior si se vuelve a pulsar su pestaña (HAL-17).
- En el filtro de movimientos, la fecha final no puede ser posterior al momento en que se abrió el historial (HAL-09).

## Limitaciones identificadas

**Transferencias**

- El enunciado del taller pide automatizar transferencias, pero la aplicación no tiene esa opción para ningún rol (EXP-056, HAL-13). No se escribieron criterios para una pantalla inexistente.
- Historia propuesta fuera del alcance: HU-F01 — Transferir dinero entre cuentas. Como cliente, quiero transferir dinero de una de mis cuentas a otra, para mover mi saldo sin tener que hacer un retiro y un depósito por separado. Propuesta para conversar con el docente. La aplicación no ofrece transferencias (EXP-056, HAL-13); por eso esta historia no tiene criterios de aceptación ni hace parte de lo que se automatizará.

**Comportamientos pendientes de verificación**

- Después de cerrar sesión y regresar con el botón Atrás, el punto 2 confirma que la cuenta vuelve a mostrarse (HAL-02), pero no deja registrado si se pueden hacer operaciones desde esa vista. CA-HU07-02 exige que la cuenta no se muestre, así que cubre ambos casos.

**Reglas por confirmar con el equipo o el docente**

- RN-HU01-09: La validación de duplicados no debería distinguir entre mayúsculas y minúsculas.
- RN-HU01-10: El nombre y el apellido no deberían aceptar valores compuestos solo por números.
- RN-HU04-03: Antes de eliminar un cliente, el sistema debería pedir una confirmación.
- RN-HU08-07: Actualmente solo se aceptan montos enteros: los valores con decimales se rechazan.
- RN-HU09-07: El monto solo admite números enteros; no es posible escribir letras.
- RN-HU12-03: Después del reinicio, el saldo de la cuenta queda en cero.
- RN-HU12-04: Antes de reiniciar, el sistema debería pedir una confirmación, porque la acción no se puede deshacer.

**Criterios que la aplicación no cumple hoy**

- CA-HU01-07 — Impedir el registro con datos que solo contienen espacios (No cumple (HAL-06)).
- CA-HU07-02 — No mostrar la cuenta al regresar después de cerrar sesión (No cumple (HAL-02)).
- CA-HU07-03 — Impedir el acceso a la cuenta sin iniciar sesión (No cumple (HAL-01)).
- CA-HU08-04 — Rechazar un depósito de cero (No cumple (HAL-03, HAL-04)).
- CA-HU08-05 — Rechazar un depósito negativo (No cumple (HAL-03)).
- CA-HU09-05 — Rechazar un retiro de cero (No cumple (HAL-05)).
- CA-HU09-06 — Rechazar un retiro negativo (No cumple (HAL-05)).
- CA-HU11-04 — Avisar cuando la fecha final no se puede usar (No cumple (HAL-09)).

**Otras diferencias**

- El administrador ingresa sin credenciales y no tiene opción de cerrar sesión (HAL-16).
- Los montos solo aceptan números enteros; no hay definición sobre el manejo de centavos (HAL-15).

## Fuera del alcance

- Transferencias entre cuentas. El enunciado del taller las menciona, pero la aplicación no las ofrece (EXP-056, HAL-13). Se deja una historia propuesta, HU-F01, separada del alcance y sin criterios de aceptación.
- Ingreso del administrador. Es un botón sin credenciales (EXP-002, HAL-16), por lo que no tiene reglas propias que verificar; se usa como paso previo de las historias HU-01 a HU-04.
- Navegación general (botón Home e historial del navegador) y persistencia de datos en el navegador (EXP-004, EXP-006). No son necesidades del usuario, pero se tienen en cuenta en las consideraciones para la automatización.
- Pruebas de rendimiento, accesibilidad, compatibilidad entre navegadores y seguridad especializada.

## Fuentes

- docs/Exploracion_Manual_GlobalSQA_Banking.docx — Informe de exploración manual del punto 2.
- docs/03_Resultados_Exploracion/Matriz_Exploracion.md — Escenarios EXP-001 a EXP-056 (referencia principal).
- docs/03_Resultados_Exploracion/Hallazgos.md — Hallazgos HAL-01 a HAL-17.
- docs/03_Resultados_Exploracion/Inventario_Evidencias.md — Relación de capturas por escenario.
- docs/03_Resultados_Exploracion/Mapa_Navegacion.png — Diagrama de navegación.
- GlobalSQA Banking Project: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login
- Enunciado del Taller 2 y distribución del trabajo del equipo (punto 3). El enunciado no está incluido en el repositorio; se tomaron las indicaciones del equipo.
- Proyecto ReservaHub (Fábrica Escuela, Sprint 2) — referencia de estilo para historias, reglas y criterios; no está incluido en este repositorio.
