# Matriz de exploración — GlobalSQA Banking

Exploración manual ejecutada el 14 de noviembre de 2026 sobre https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login.

**Resumen:** 56 escenarios · 13 exitosos · 21 alternativos · 22 negativos · 46 Conformes · 9 No conformes · 1 Bloqueado.

## Matriz consolidada

| ID | Escenario | Módulo | Actor | Tipo | Estado | Hallazgo |
|---|---|---|---|---|---|---|
| EXP-001 | Carga de la pantalla principal del banco | A. Inicio y navegación | Visitante (sin sesión) | Exitoso | Conforme | No aplica |
| EXP-002 | Ingreso como administrador (Bank Manager Login) | A. Inicio y navegación | Administrador bancario (Bank Manager) | Exitoso | Conforme | HAL-16 (observación) |
| EXP-003 | Ingreso al módulo cliente sin seleccionar un nombre | A. Inicio y navegación | Visitante (sin sesión) | Negativo | Conforme | No aplica |
| EXP-004 | Regreso a la pantalla principal con el botón Home | A. Inicio y navegación | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-005 | Acceso directo por URL a rutas internas sin sesión | A. Inicio y navegación | Visitante (sin sesión) | Negativo | No conforme | HAL-01 |
| EXP-006 | Persistencia de datos al recargar y aislamiento entre sesiones | A. Inicio y navegación | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-007 | Registrar un cliente con datos válidos | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Exitoso | Conforme | No aplica |
| EXP-008 | Registrar un segundo cliente con nombre y apellido distintos | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-009 | Enviar el formulario Add Customer completamente vacío | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-010 | Omitir un campo obligatorio (Post Code y Last Name) | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-011 | Registrar un cliente con caracteres especiales | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Alternativo | Conforme | HAL-08 |
| EXP-012 | Registrar un cliente con nombre y apellido numéricos | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Negativo | No conforme | HAL-08 |
| EXP-013 | Enviar campos que solo contienen espacios en blanco | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Negativo | No conforme | HAL-06 |
| EXP-014 | Registrar un cliente duplicado (mismos datos) | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-015 | Duplicados parciales: otro código postal y otra capitalización | B1. Registro de clientes (Add Customer) | Administrador bancario (Bank Manager) | Alternativo | No conforme | HAL-07 |
| EXP-016 | Abrir una cuenta en Dollar para un cliente de prueba | B2. Apertura de cuentas (Open Account) | Administrador bancario (Bank Manager) | Exitoso | Conforme | No aplica |
| EXP-017 | Abrir una segunda cuenta en Pound para el mismo cliente | B2. Apertura de cuentas (Open Account) | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-018 | Abrir una cuenta en Rupee para otro cliente y verificar la asociación | B2. Apertura de cuentas (Open Account) | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-019 | Procesar la apertura de cuenta sin seleccionar cliente | B2. Apertura de cuentas (Open Account) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-020 | Procesar la apertura de cuenta sin seleccionar moneda | B2. Apertura de cuentas (Open Account) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-021 | Consultar el listado de clientes | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Exitoso | Conforme | No aplica |
| EXP-022 | Buscar cliente por nombre | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Exitoso | Conforme | No aplica |
| EXP-023 | Buscar cliente por apellido | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Exitoso | Conforme | No aplica |
| EXP-024 | Buscar con coincidencia parcial | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-025 | Buscar por código postal y por número de cuenta | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Alternativo | Conforme | No aplica |
| EXP-026 | Buscar un cliente inexistente | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Negativo | Conforme | No aplica |
| EXP-027 | Ordenar el listado por columnas | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Alternativo | Conforme | HAL-12 (observación) |
| EXP-028 | Eliminar un cliente de prueba | B3. Gestión de clientes (Customers) | Administrador bancario (Bank Manager) | Exitoso | Conforme | HAL-11 (observación) |
| EXP-029 | Inicio de sesión de un cliente existente | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Exitoso | Conforme | No aplica |
| EXP-030 | Cambio entre las cuentas del cliente | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-031 | Ingreso de un cliente sin cuentas | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-032 | Cerrar sesión del cliente | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Exitoso | Conforme | No aplica |
| EXP-033 | Comportamiento después de cerrar sesión (botón Atrás) | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Negativo | No conforme | HAL-02 |
| EXP-034 | Uso del botón Home durante la sesión del cliente | C1. Inicio y cierre de sesión del cliente | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-035 | Depósito válido | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Exitoso | Conforme | No aplica |
| EXP-036 | Depósitos consecutivos | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-037 | Depósito de cero | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Negativo | No conforme | HAL-03, HAL-04 |
| EXP-038 | Depósito negativo | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Negativo | No conforme | HAL-03 |
| EXP-039 | Depósito con el campo vacío | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Negativo | Conforme | No aplica |
| EXP-040 | Depósito con valor decimal | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Alternativo | Conforme | HAL-15 (observación) |
| EXP-041 | Depósito con caracteres no numéricos | C2. Depósitos (Deposit) | Cliente bancario (Customer) | Negativo | Conforme | HAL-15 (observación) |
| EXP-042 | Retiro válido | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Exitoso | Conforme | No aplica |
| EXP-043 | Retiro superior al saldo | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Negativo | Conforme | No aplica |
| EXP-044 | Retiro del saldo total exacto | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-045 | Retiro de cero | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Negativo | No conforme | HAL-05 |
| EXP-046 | Retiro negativo | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Negativo | No conforme | HAL-05 |
| EXP-047 | Retiro con el campo vacío | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Negativo | Conforme | No aplica |
| EXP-048 | Retiro con valores inválidos (decimal y no numérico) | C3. Retiros (Withdrawl) | Cliente bancario (Customer) | Negativo | Conforme | HAL-15 (observación) |
| EXP-049 | Consultar el historial de movimientos | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Exitoso | Conforme | No aplica |
| EXP-050 | Consultar movimientos de una cuenta sin transacciones | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-051 | Filtrar movimientos por rango de fechas | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Alternativo | Conforme | HAL-09 |
| EXP-052 | Filtro de fechas sin resultados | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Negativo | Conforme | No aplica |
| EXP-053 | Ordenar movimientos por Date-Time | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Alternativo | Conforme | HAL-12 (observación) |
| EXP-054 | Navegación con Back y controles de desplazamiento | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Alternativo | Conforme | No aplica |
| EXP-055 | Reiniciar movimientos con Reset | C4. Movimientos (Transactions) | Cliente bancario (Customer) | Alternativo | Conforme | HAL-10 |
| EXP-056 | Verificar la existencia de la funcionalidad de transferencias | C5. Transferencias | Cliente bancario (Customer) / Administrador bancario (Bank Manager) | Negativo | Bloqueado | HAL-13 (limitación) |

## Detalle por escenario

Cada caso incluye la información necesaria para redactar historias de usuario y escenarios Gherkin (Dado = precondiciones/datos, Cuando = procedimiento, Entonces = resultado esperado).

### A. Inicio y navegación

#### EXP-001 — Carga de la pantalla principal del banco

- **Actor:** Visitante (sin sesión)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar que la aplicación carga y muestra las opciones de acceso disponibles.
- **Precondiciones:** Conexión a internet; navegador sin datos previos del sitio.
- **Datos de entrada:** URL de la aplicación.
- **Pasos:**
  1. Abrir la URL de GlobalSQA Banking en el navegador.
  2. Esperar la carga completa de la página.
  3. Observar los elementos disponibles.
- **Resultado esperado:** Se muestra la pantalla principal del banco con las opciones de acceso para cliente y administrador.
- **Resultado observado:** Carga la página con título de pestaña 'XYZ Bank'. Se muestran los botones Home, Customer Login y Bank Manager Login. El botón Logout no es visible. La ruta queda en #/login.
- **Estado:** Conforme
- **Evidencias:** [EXP-001_inicio_banco.png](../02_Capturas_Anotadas/EXP-001_inicio_banco.png)
- **Observaciones:** El nombre visible de la aplicación es 'XYZ Bank'. No existe formulario de usuario/contraseña.
- **Posible defecto:** No aplica

#### EXP-002 — Ingreso como administrador (Bank Manager Login)

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar el acceso al módulo del administrador bancario.
- **Precondiciones:** Pantalla principal cargada (EXP-001).
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Pulsar el botón Bank Manager Login.
  2. Observar la vista y las opciones disponibles.
- **Resultado esperado:** Se accede a la vista del administrador con sus funcionalidades.
- **Resultado observado:** La ruta cambia a #/manager y se muestran las pestañas Add Customer, Open Account y Customers. No se solicitan credenciales. El botón Logout no aparece en la vista del administrador.
- **Estado:** Conforme
- **Evidencias:** [EXP-002_acceso_administrador.png](../02_Capturas_Anotadas/EXP-002_acceso_administrador.png)
- **Observaciones:** El acceso no requiere autenticación (característica de la aplicación de demostración). Para salir del módulo solo está disponible el botón Home.
- **Posible defecto:** HAL-16 (observación)

#### EXP-003 — Ingreso al módulo cliente sin seleccionar un nombre

- **Actor:** Visitante (sin sesión)
- **Tipo de flujo:** Negativo
- **Objetivo:** Comprobar si es posible iniciar sesión como cliente sin seleccionar un nombre.
- **Precondiciones:** Pantalla principal cargada.
- **Datos de entrada:** Opción '---Your Name---' (vacía) y luego 'Harry Potter'.
- **Pasos:**
  1. Pulsar Customer Login.
  2. Observar el formulario sin seleccionar ningún cliente.
  3. Seleccionar 'Harry Potter' en el campo Your Name.
  4. Volver a seleccionar la opción '---Your Name---'.
- **Resultado esperado:** El sistema impide iniciar sesión mientras no se seleccione un cliente.
- **Resultado observado:** En #/customer el selector lista 5 clientes precargados (Hermoine Granger, Harry Potter, Ron Weasly, Albus Dumbledore, Neville Longbottom). Sin selección, el botón Login no se muestra. Al seleccionar un cliente aparece el botón Login y al volver a '---Your Name---' se oculta de nuevo.
- **Estado:** Conforme
- **Evidencias:** [EXP-003_login_cliente_sin_seleccion.png](../02_Capturas_Anotadas/EXP-003_login_cliente_sin_seleccion.png), [EXP-003_login_cliente_con_seleccion.png](../02_Capturas_Anotadas/EXP-003_login_cliente_con_seleccion.png), [EXP-003_login_cliente_deseleccion.png](../02_Capturas_Anotadas/EXP-003_login_cliente_deseleccion.png)
- **Observaciones:** La restricción se implementa ocultando el botón, no mediante un mensaje de validación.
- **Posible defecto:** No aplica

#### EXP-004 — Regreso a la pantalla principal con el botón Home

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar la navegación de regreso a la pantalla principal desde el módulo administrador.
- **Precondiciones:** Administrador en la pestaña Open Account (#/manager/openAccount).
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Ingresar como administrador y abrir la pestaña Open Account.
  2. Pulsar el botón Home.
  3. Pulsar el botón Atrás del navegador.
- **Resultado esperado:** Home lleva a la pantalla principal; el historial del navegador permite volver a la vista anterior.
- **Resultado observado:** Home lleva a #/login con las opciones Customer Login y Bank Manager Login. Con el botón Atrás del navegador se regresa a #/manager/openAccount con el formulario visible.
- **Estado:** Conforme
- **Evidencias:** [EXP-004_home_antes.png](../02_Capturas_Anotadas/EXP-004_home_antes.png), [EXP-004_home_despues.png](../02_Capturas_Anotadas/EXP-004_home_despues.png), [EXP-004_home_atras_navegador.png](../02_Capturas_Anotadas/EXP-004_home_atras_navegador.png)
- **Observaciones:** La aplicación es de una sola página (SPA) con rutas basadas en '#'.
- **Posible defecto:** No aplica

#### EXP-005 — Acceso directo por URL a rutas internas sin sesión

- **Actor:** Visitante (sin sesión)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el comportamiento al abrir rutas internas sin pasar por la pantalla principal.
- **Precondiciones:** Sesión de navegador nueva para cada ruta (sin datos ni sesión previos).
- **Datos de entrada:** Rutas #/manager/addCust, #/account y #/listTx.
- **Pasos:**
  1. Abrir una sesión nueva del navegador directamente en .../#/manager/addCust.
  2. Repetir con .../#/account.
  3. Repetir con .../#/listTx.
  4. Observar el contenido de cada vista.
- **Resultado esperado:** Las rutas de cliente sin sesión redirigen a la pantalla principal o muestran un mensaje controlado; no se muestran datos ni plantillas sin procesar.
- **Resultado observado:** #/manager/addCust muestra el formulario del administrador sin ningún control de acceso. #/account muestra literalmente 'Welcome {{user}} !!', 'Account Number : {{accountNo}} , Balance : {{amount}} , Currency : {{currency}}', el botón Logout y las pestañas Transactions, Deposit y Withdrawl. #/listTx muestra la tabla vacía con los botones Back, Reset, Logout y los controles '<', 'Top' y '>'.
- **Estado:** No conforme
- **Evidencias:** [EXP-005_url_directa_manager.png](../02_Capturas_Anotadas/EXP-005_url_directa_manager.png), [EXP-005_url_directa_account.png](../02_Capturas_Anotadas/EXP-005_url_directa_account.png), [EXP-005_url_directa_transacciones.png](../02_Capturas_Anotadas/EXP-005_url_directa_transacciones.png)
- **Observaciones:** No se registraron errores JavaScript en la consola durante estas cargas.
- **Posible defecto:** HAL-01

#### EXP-006 — Persistencia de datos al recargar y aislamiento entre sesiones

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Determinar dónde se conservan los datos creados y si persisten tras recargar la página.
- **Precondiciones:** Clientes de prueba creados en EXP-007 a EXP-015 en la misma sesión del navegador.
- **Datos de entrada:** Búsqueda 'QAPrueba'.
- **Pasos:**
  1. En Customers, recargar la página (F5).
  2. Buscar 'QAPrueba'.
  3. Abrir una nueva sesión de navegador (contexto limpio) en #/manager/list.
  4. Buscar 'QAPrueba' y contar los clientes.
- **Resultado esperado:** Los datos creados se conservan al recargar dentro de la misma sesión.
- **Resultado observado:** Tras la recarga se conserva la ruta #/manager/list y la búsqueda devuelve 3 registros (Ana QAPrueba QA0001 con cuentas 1016 1017, Ana QAPrueba QA9999 y ana qaprueba QA0001). El almacenamiento local del navegador contiene las claves maxUserId, User, maxAccountNo, Account y Transaction. En una sesión nueva la búsqueda devuelve 0 registros y la lista contiene solo los 5 clientes precargados.
- **Estado:** Conforme
- **Evidencias:** [EXP-006_persistencia_recarga.png](../02_Capturas_Anotadas/EXP-006_persistencia_recarga.png), [EXP-006_sesion_nueva.png](../02_Capturas_Anotadas/EXP-006_sesion_nueva.png)
- **Observaciones:** Los datos viven en el localStorage de cada navegador: no se comparten entre usuarios ni sesiones. Cada sesión nueva reinicia los datos de demostración (IDs de cliente desde 6 y cuentas desde 1016).
- **Posible defecto:** No aplica

### B1. Registro de clientes (Add Customer)

#### EXP-007 — Registrar un cliente con datos válidos

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Crear un cliente nuevo y verificar su almacenamiento.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** First Name: Ana; Last Name: QAPrueba; Post Code: QA0001.
- **Pasos:**
  1. Abrir la pestaña Add Customer.
  2. Diligenciar First Name, Last Name y Post Code.
  3. Pulsar Add Customer.
  4. Aceptar la alerta.
  5. Abrir Customers y buscar 'QAPrueba'.
- **Resultado esperado:** Se muestra una confirmación con el identificador del cliente y el cliente aparece en el listado.
- **Resultado observado:** Alerta nativa: 'Customer added successfully with customer id :6'. El formulario queda vacío tras aceptar. En Customers aparece 'Ana | QAPrueba | QA0001' sin número de cuenta.
- **Estado:** Conforme
- **Evidencias:** [EXP-007_crear_cliente_formulario.png](../02_Capturas_Anotadas/EXP-007_crear_cliente_formulario.png), [EXP-007_crear_cliente_alerta.png](../02_Capturas_Anotadas/EXP-007_crear_cliente_alerta.png), [EXP-007_crear_cliente_verificacion.png](../02_Capturas_Anotadas/EXP-007_crear_cliente_verificacion.png)
- **Observaciones:** La confirmación se presenta como alerta nativa del navegador (window.alert), que deberá manejarse en la automatización.
- **Posible defecto:** No aplica

#### EXP-008 — Registrar un segundo cliente con nombre y apellido distintos

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar la creación consecutiva de clientes y la secuencia de identificadores.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login. Cliente Ana QAPrueba ya creado (id 6).
- **Datos de entrada:** First Name: Luis; Last Name: QAExplora; Post Code: QA0002.
- **Pasos:**
  1. Abrir Add Customer.
  2. Diligenciar los tres campos.
  3. Pulsar Add Customer y aceptar la alerta.
- **Resultado esperado:** Se crea el cliente con el siguiente identificador disponible.
- **Resultado observado:** Alerta: 'Customer added successfully with customer id :7'. Los campos quedan vacíos.
- **Estado:** Conforme
- **Evidencias:** [EXP-008_segundo_cliente_formulario.png](../02_Capturas_Anotadas/EXP-008_segundo_cliente_formulario.png), [EXP-008_segundo_cliente_alerta.png](../02_Capturas_Anotadas/EXP-008_segundo_cliente_alerta.png)
- **Observaciones:** Los identificadores son consecutivos dentro de la sesión.
- **Posible defecto:** No aplica

#### EXP-009 — Enviar el formulario Add Customer completamente vacío

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar la validación de campos obligatorios.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** Todos los campos vacíos.
- **Pasos:**
  1. Abrir Add Customer.
  2. Sin diligenciar campos, pulsar Add Customer.
- **Resultado esperado:** El sistema no registra el cliente e informa que los campos son obligatorios.
- **Resultado observado:** El navegador muestra la validación nativa 'Completa este campo' sobre First Name. No aparece ninguna alerta y no se crea el cliente.
- **Estado:** Conforme
- **Evidencias:** [EXP-009_formulario_vacio.png](../02_Capturas_Anotadas/EXP-009_formulario_vacio.png)
- **Observaciones:** La validación es HTML5 (atributo required); el texto depende del idioma del navegador.
- **Posible defecto:** No aplica

#### EXP-010 — Omitir un campo obligatorio (Post Code y Last Name)

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar que cada campo es obligatorio de manera individual.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** Intento 1: First Name 'Carla', Last Name 'QAOmision', Post Code vacío. Intento 2: First Name 'Carla', Last Name vacío, Post Code 'QA0003'.
- **Pasos:**
  1. Diligenciar First Name y Last Name dejando Post Code vacío y pulsar Add Customer.
  2. Vaciar Last Name, diligenciar Post Code y pulsar Add Customer.
- **Resultado esperado:** En ambos casos se bloquea el registro indicando el campo faltante.
- **Resultado observado:** Intento 1: validación 'Completa este campo' sobre Post Code. Intento 2: validación 'Completa este campo' sobre Last Name. No se presentó alerta ni se creó cliente.
- **Estado:** Conforme
- **Evidencias:** [EXP-010_sin_post_code.png](../02_Capturas_Anotadas/EXP-010_sin_post_code.png), [EXP-010_sin_last_name.png](../02_Capturas_Anotadas/EXP-010_sin_last_name.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-011 — Registrar un cliente con caracteres especiales

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el tratamiento de tildes, guiones, apóstrofes y símbolos.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** First Name: José-María; Last Name: O'Brien Ñúñez; Post Code: @#$%&*.
- **Pasos:**
  1. Abrir Add Customer.
  2. Diligenciar los campos con los valores indicados.
  3. Pulsar Add Customer y aceptar la alerta.
  4. Buscar 'Ñúñez' en Customers.
- **Resultado esperado:** Los nombres con tildes, guiones y apóstrofes se aceptan y se almacenan sin alteraciones.
- **Resultado observado:** Alerta: 'Customer added successfully with customer id :8'. En Customers aparece 'José-María | O'Brien Ñúñez | @#$%&*' con los caracteres conservados.
- **Estado:** Conforme
- **Evidencias:** [EXP-011_caracteres_especiales_formulario.png](../02_Capturas_Anotadas/EXP-011_caracteres_especiales_formulario.png), [EXP-011_caracteres_especiales_alerta.png](../02_Capturas_Anotadas/EXP-011_caracteres_especiales_alerta.png), [EXP-011_caracteres_especiales_lista.png](../02_Capturas_Anotadas/EXP-011_caracteres_especiales_lista.png)
- **Observaciones:** El código postal compuesto solo por símbolos también fue aceptado: no existe validación de formato (ver HAL-08).
- **Posible defecto:** HAL-08

#### EXP-012 — Registrar un cliente con nombre y apellido numéricos

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar si el sistema valida que nombre y apellido no sean numéricos.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** First Name: 12345; Last Name: 67890; Post Code: 00000.
- **Pasos:**
  1. Abrir Add Customer.
  2. Diligenciar los campos con valores numéricos.
  3. Pulsar Add Customer.
  4. Buscar '67890' en Customers.
- **Resultado esperado:** El sistema rechaza nombres y apellidos compuestos únicamente por dígitos.
- **Resultado observado:** Alerta: 'Customer added successfully with customer id :9'. El registro '12345 | 67890 | 00000' queda almacenado.
- **Estado:** No conforme
- **Evidencias:** [EXP-012_nombres_numericos_formulario.png](../02_Capturas_Anotadas/EXP-012_nombres_numericos_formulario.png), [EXP-012_nombres_numericos_alerta.png](../02_Capturas_Anotadas/EXP-012_nombres_numericos_alerta.png), [EXP-012_nombres_numericos_lista.png](../02_Capturas_Anotadas/EXP-012_nombres_numericos_lista.png)
- **Observaciones:** La regla de negocio sobre el formato de nombres no está documentada; se registra como posible defecto por confirmar.
- **Posible defecto:** HAL-08

#### EXP-013 — Enviar campos que solo contienen espacios en blanco

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el tratamiento de valores compuestos solo por espacios.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** Tres espacios en First Name, Last Name y Post Code.
- **Pasos:**
  1. Abrir Add Customer.
  2. Escribir tres espacios en cada campo.
  3. Pulsar Add Customer.
  4. Revisar el listado Customers.
- **Resultado esperado:** El sistema rechaza el registro e indica que los campos son obligatorios o inválidos.
- **Resultado observado:** La validación nativa no se activa (el campo no está vacío). Aparece la alerta 'Please check the details. Customer may be duplicate.' No se crea el cliente (el listado mantiene 9 registros) y los campos conservan los espacios.
- **Estado:** No conforme
- **Evidencias:** [EXP-013_solo_espacios_formulario.png](../02_Capturas_Anotadas/EXP-013_solo_espacios_formulario.png), [EXP-013_solo_espacios_alerta.png](../02_Capturas_Anotadas/EXP-013_solo_espacios_alerta.png), [EXP-013_solo_espacios_lista.png](../02_Capturas_Anotadas/EXP-013_solo_espacios_lista.png)
- **Observaciones:** El registro se bloquea, pero el mensaje es engañoso: los datos no corresponden a un cliente duplicado.
- **Posible defecto:** HAL-06

#### EXP-014 — Registrar un cliente duplicado (mismos datos)

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el control de clientes duplicados.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login. Cliente Ana QAPrueba QA0001 ya existente (id 6).
- **Datos de entrada:** First Name: Ana; Last Name: QAPrueba; Post Code: QA0001.
- **Pasos:**
  1. Abrir Add Customer.
  2. Diligenciar exactamente los datos del cliente existente.
  3. Pulsar Add Customer.
  4. Buscar 'QAPrueba' en Customers.
- **Resultado esperado:** El sistema rechaza el registro duplicado con un mensaje.
- **Resultado observado:** Alerta: 'Please check the details. Customer may be duplicate.' Los datos permanecen en el formulario. La búsqueda sigue mostrando un único registro 'Ana | QAPrueba | QA0001'.
- **Estado:** Conforme
- **Evidencias:** [EXP-014_cliente_duplicado_formulario.png](../02_Capturas_Anotadas/EXP-014_cliente_duplicado_formulario.png), [EXP-014_cliente_duplicado_alerta.png](../02_Capturas_Anotadas/EXP-014_cliente_duplicado_alerta.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-015 — Duplicados parciales: otro código postal y otra capitalización

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Determinar el criterio de detección de duplicados.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login. Cliente Ana QAPrueba QA0001 ya existente.
- **Datos de entrada:** Intento 1: Ana / QAPrueba / QA9999. Intento 2: ana / qaprueba / QA0001.
- **Pasos:**
  1. Registrar Ana QAPrueba con Post Code QA9999.
  2. Registrar ana qaprueba (minúsculas) con Post Code QA0001.
  3. Buscar 'prueba' en Customers.
- **Resultado esperado:** Un homónimo con otro código postal puede aceptarse; los mismos datos con distinta capitalización deberían detectarse como duplicado.
- **Resultado observado:** Intento 1: 'Customer added successfully with customer id :10'. Intento 2: 'Customer added successfully with customer id :11'. La búsqueda devuelve tres registros: Ana QAPrueba QA0001, Ana QAPrueba QA9999 y ana qaprueba QA0001.
- **Estado:** No conforme
- **Evidencias:** [EXP-015_duplicado_otro_postal_formulario.png](../02_Capturas_Anotadas/EXP-015_duplicado_otro_postal_formulario.png), [EXP-015_duplicado_otro_postal_alerta.png](../02_Capturas_Anotadas/EXP-015_duplicado_otro_postal_alerta.png), [EXP-015_duplicado_minusculas_formulario.png](../02_Capturas_Anotadas/EXP-015_duplicado_minusculas_formulario.png), [EXP-015_duplicado_minusculas_alerta.png](../02_Capturas_Anotadas/EXP-015_duplicado_minusculas_alerta.png), [EXP-015_duplicados_lista.png](../02_Capturas_Anotadas/EXP-015_duplicados_lista.png)
- **Observaciones:** La detección de duplicados distingue mayúsculas y minúsculas. Además, en los selectores de Open Account y Customer Login aparecen dos entradas idénticas 'Ana QAPrueba', imposibles de diferenciar.
- **Posible defecto:** HAL-07

### B2. Apertura de cuentas (Open Account)

#### EXP-016 — Abrir una cuenta en Dollar para un cliente de prueba

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Crear una cuenta bancaria y obtener el número generado.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login. Cliente Ana QAPrueba creado.
- **Datos de entrada:** Customer: Ana QAPrueba; Currency: Dollar.
- **Pasos:**
  1. Abrir la pestaña Open Account.
  2. Seleccionar el cliente y la moneda.
  3. Pulsar Process y aceptar la alerta.
- **Resultado esperado:** Se crea la cuenta y se informa su número.
- **Resultado observado:** El selector Currency ofrece Dollar, Pound y Rupee. Alerta: 'Account created successfully with account Number :1016'. Ambos selectores vuelven a sus opciones vacías.
- **Estado:** Conforme
- **Evidencias:** [EXP-016_abrir_cuenta_dollar_formulario.png](../02_Capturas_Anotadas/EXP-016_abrir_cuenta_dollar_formulario.png), [EXP-016_abrir_cuenta_dollar_alerta.png](../02_Capturas_Anotadas/EXP-016_abrir_cuenta_dollar_alerta.png)
- **Observaciones:** Los números de cuenta continúan después de las cuentas precargadas (1001 a 1015).
- **Posible defecto:** No aplica

#### EXP-017 — Abrir una segunda cuenta en Pound para el mismo cliente

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar que un cliente puede tener varias cuentas en distintas monedas.
- **Precondiciones:** Cuenta 1016 creada para Ana QAPrueba (EXP-016).
- **Datos de entrada:** Customer: Ana QAPrueba; Currency: Pound.
- **Pasos:**
  1. Abrir Open Account.
  2. Seleccionar Ana QAPrueba y Pound.
  3. Pulsar Process y aceptar la alerta.
- **Resultado esperado:** Se crea una segunda cuenta para el mismo cliente.
- **Resultado observado:** Alerta: 'Account created successfully with account Number :1017'.
- **Estado:** Conforme
- **Evidencias:** [EXP-017_abrir_cuenta_pound_formulario.png](../02_Capturas_Anotadas/EXP-017_abrir_cuenta_pound_formulario.png), [EXP-017_abrir_cuenta_pound_alerta.png](../02_Capturas_Anotadas/EXP-017_abrir_cuenta_pound_alerta.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-018 — Abrir una cuenta en Rupee para otro cliente y verificar la asociación

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar que cada cuenta queda asociada al cliente seleccionado.
- **Precondiciones:** Cliente Luis QAExplora creado (EXP-008).
- **Datos de entrada:** Customer: Luis QAExplora; Currency: Rupee; búsqueda 'QA'.
- **Pasos:**
  1. Abrir Open Account.
  2. Seleccionar Luis QAExplora y Rupee.
  3. Pulsar Process y aceptar la alerta.
  4. En Customers buscar 'QA'.
- **Resultado esperado:** La cuenta se crea y el listado muestra cada número de cuenta junto a su cliente.
- **Resultado observado:** Alerta: 'Account created successfully with account Number :1018'. En Customers: Ana QAPrueba QA0001 → '1016 1017'; Luis QAExplora → '1018'; los demás clientes de prueba sin cuentas.
- **Estado:** Conforme
- **Evidencias:** [EXP-018_abrir_cuenta_rupee_formulario.png](../02_Capturas_Anotadas/EXP-018_abrir_cuenta_rupee_formulario.png), [EXP-018_abrir_cuenta_rupee_alerta.png](../02_Capturas_Anotadas/EXP-018_abrir_cuenta_rupee_alerta.png), [EXP-018_cuentas_asociadas.png](../02_Capturas_Anotadas/EXP-018_cuentas_asociadas.png)
- **Observaciones:** La moneda de cada cuenta no se muestra en el listado del administrador; solo es visible desde la vista del cliente.
- **Posible defecto:** No aplica

#### EXP-019 — Procesar la apertura de cuenta sin seleccionar cliente

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar la validación del cliente obligatorio.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** Customer: (vacío); Currency: Dollar.
- **Pasos:**
  1. Abrir Open Account.
  2. Dejar '---Customer Name---' y seleccionar Dollar.
  3. Pulsar Process.
- **Resultado esperado:** No se crea la cuenta y se solicita seleccionar un cliente.
- **Resultado observado:** Validación nativa 'Selecciona un elemento de la lista' sobre Customer. No aparece alerta ni se crea cuenta.
- **Estado:** Conforme
- **Evidencias:** [EXP-019_sin_cliente.png](../02_Capturas_Anotadas/EXP-019_sin_cliente.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-020 — Procesar la apertura de cuenta sin seleccionar moneda

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar la validación de la moneda obligatoria.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login.
- **Datos de entrada:** Customer: Luis QAExplora; Currency: (vacío).
- **Pasos:**
  1. Abrir Open Account.
  2. Seleccionar Luis QAExplora y dejar '---Currency---'.
  3. Pulsar Process.
- **Resultado esperado:** No se crea la cuenta y se solicita seleccionar la moneda.
- **Resultado observado:** Validación nativa 'Selecciona un elemento de la lista' sobre Currency. No aparece alerta ni se crea cuenta.
- **Estado:** Conforme
- **Evidencias:** [EXP-020_sin_moneda.png](../02_Capturas_Anotadas/EXP-020_sin_moneda.png)
- **Observaciones:** Al volver a pulsar la pestaña Open Account estando ya en ella, el formulario conserva la selección anterior (Currency = Dollar del caso EXP-019). En una primera iteración de la exploración esto provocó que se creara una cuenta no prevista; la iteración se descartó y se repitió reiniciando el formulario de forma explícita.
- **Posible defecto:** No aplica

### B3. Gestión de clientes (Customers)

#### EXP-021 — Consultar el listado de clientes

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar la información mostrada en Customers.
- **Precondiciones:** Navegador con la aplicación abierta en la pantalla principal; ingreso mediante Bank Manager Login. Clientes de prueba creados (EXP-007 a EXP-018).
- **Datos de entrada:** Sin filtro.
- **Pasos:**
  1. Abrir la pestaña Customers.
  2. Revisar las columnas y registros.
  3. Desplazar la tabla hasta el final.
- **Resultado esperado:** Se listan todos los clientes con su información y cuentas.
- **Resultado observado:** Columnas: First Name, Last Name, Post Code, Account Number, Delete Customer. Se listan 11 clientes: los 5 precargados (cuentas 1001 a 1015) y los 6 de prueba con sus cuentas (1016 1017 y 1018). Cada fila tiene un botón Delete. La tabla tiene desplazamiento interno.
- **Estado:** Conforme
- **Evidencias:** [EXP-021_listado_clientes.png](../02_Capturas_Anotadas/EXP-021_listado_clientes.png), [EXP-021_listado_clientes_final.png](../02_Capturas_Anotadas/EXP-021_listado_clientes_final.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-022 — Buscar cliente por nombre

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar la búsqueda por nombre.
- **Precondiciones:** Vista Customers abierta.
- **Datos de entrada:** Texto de búsqueda: Harry.
- **Pasos:**
  1. Escribir 'Harry' en Search Customer.
- **Resultado esperado:** Se muestran solo los clientes cuyo nombre coincide.
- **Resultado observado:** Un resultado: 'Harry | Potter | E725JB | 1004 1005 1006'. El filtro se aplica mientras se escribe.
- **Estado:** Conforme
- **Evidencias:** [EXP-022_buscar_nombre.png](../02_Capturas_Anotadas/EXP-022_buscar_nombre.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-023 — Buscar cliente por apellido

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar la búsqueda por apellido.
- **Precondiciones:** Vista Customers abierta.
- **Datos de entrada:** Texto de búsqueda: QAExplora.
- **Pasos:**
  1. Escribir 'QAExplora' en Search Customer.
- **Resultado esperado:** Se muestra el cliente con ese apellido.
- **Resultado observado:** Un resultado: 'Luis | QAExplora | QA0002 | 1018'.
- **Estado:** Conforme
- **Evidencias:** [EXP-023_buscar_apellido.png](../02_Capturas_Anotadas/EXP-023_buscar_apellido.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-024 — Buscar con coincidencia parcial

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar la búsqueda por fragmentos de texto.
- **Precondiciones:** Vista Customers abierta.
- **Datos de entrada:** Texto de búsqueda: ott.
- **Pasos:**
  1. Escribir 'ott' en Search Customer.
- **Resultado esperado:** Se muestran los clientes que contienen el fragmento en alguno de sus datos.
- **Resultado observado:** Dos resultados: 'Harry Potter' y 'Neville Longbottom'.
- **Estado:** Conforme
- **Evidencias:** [EXP-024_buscar_parcial.png](../02_Capturas_Anotadas/EXP-024_buscar_parcial.png)
- **Observaciones:** La búsqueda es por subcadena.
- **Posible defecto:** No aplica

#### EXP-025 — Buscar por código postal y por número de cuenta

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Determinar qué columnas considera el buscador.
- **Precondiciones:** Vista Customers abierta.
- **Datos de entrada:** Textos: E725JB y 1016.
- **Pasos:**
  1. Escribir 'E725JB'.
  2. Reemplazar por '1016'.
- **Resultado esperado:** El buscador filtra también por las demás columnas visibles.
- **Resultado observado:** 'E725JB' devuelve a Harry Potter. '1016' devuelve a 'Ana | QAPrueba | QA0001 | 1016 1017'.
- **Estado:** Conforme
- **Evidencias:** [EXP-025_buscar_codigo_postal.png](../02_Capturas_Anotadas/EXP-025_buscar_codigo_postal.png), [EXP-025_buscar_numero_cuenta.png](../02_Capturas_Anotadas/EXP-025_buscar_numero_cuenta.png)
- **Observaciones:** El filtro aplica sobre todos los campos del cliente, incluidos los números de cuenta.
- **Posible defecto:** No aplica

#### EXP-026 — Buscar un cliente inexistente

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el comportamiento sin coincidencias.
- **Precondiciones:** Vista Customers abierta.
- **Datos de entrada:** Texto: ZZZNoExiste.
- **Pasos:**
  1. Escribir 'ZZZNoExiste' en Search Customer.
- **Resultado esperado:** La tabla queda sin registros.
- **Resultado observado:** La tabla muestra solo los encabezados (0 filas). No se presenta ningún mensaje de 'sin resultados'.
- **Estado:** Conforme
- **Evidencias:** [EXP-026_buscar_inexistente.png](../02_Capturas_Anotadas/EXP-026_buscar_inexistente.png)
- **Observaciones:** La ausencia de un mensaje informativo es una oportunidad de mejora de usabilidad.
- **Posible defecto:** No aplica

#### EXP-027 — Ordenar el listado por columnas

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el ordenamiento al pulsar los encabezados.
- **Precondiciones:** Vista Customers con 11 clientes.
- **Datos de entrada:** Encabezados First Name y Post Code.
- **Pasos:**
  1. Pulsar el encabezado First Name.
  2. Pulsarlo nuevamente.
  3. Pulsar el encabezado Post Code.
- **Resultado esperado:** El listado se ordena por la columna seleccionada y alterna el sentido.
- **Resultado observado:** Primer clic: orden descendente (Ron, Neville, Luis, ... , 12345). Segundo clic: ascendente (12345, Albus, Ana, ...). Post Code: orden descendente (QA9999 ... 00000). Tras pulsar un encabezado, la URL del navegador cambia a '.../BankingProject/#'.
- **Estado:** Conforme
- **Evidencias:** [EXP-027_ordenar_nombre_1.png](../02_Capturas_Anotadas/EXP-027_ordenar_nombre_1.png), [EXP-027_ordenar_nombre_2.png](../02_Capturas_Anotadas/EXP-027_ordenar_nombre_2.png)
- **Observaciones:** El primer clic ordena de forma descendente. Los encabezados son enlaces href='#'.
- **Posible defecto:** HAL-12 (observación)

#### EXP-028 — Eliminar un cliente de prueba

- **Actor:** Administrador bancario (Bank Manager)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar la eliminación de clientes y su efecto en las demás vistas.
- **Precondiciones:** Cliente de prueba Luis QAExplora (cuenta 1018).
- **Datos de entrada:** Cliente: Luis QAExplora.
- **Pasos:**
  1. Buscar 'QAExplora' en Customers.
  2. Pulsar Delete en la fila.
  3. Limpiar la búsqueda y contar los registros.
  4. Revisar los selectores de Open Account y Customer Login.
- **Resultado esperado:** El cliente desaparece del listado y de los selectores.
- **Resultado observado:** El registro desaparece inmediatamente, sin confirmación previa. El total pasa de 11 a 10 clientes. Luis QAExplora ya no aparece en los selectores de Open Account ni de Customer Login (verificado leyendo las opciones de ambos selectores: 10 clientes listados).
- **Estado:** Conforme
- **Evidencias:** [EXP-028_eliminar_antes.png](../02_Capturas_Anotadas/EXP-028_eliminar_antes.png), [EXP-028_eliminar_despues.png](../02_Capturas_Anotadas/EXP-028_eliminar_despues.png), [EXP-028_eliminar_selector_cliente.png](../02_Capturas_Anotadas/EXP-028_eliminar_selector_cliente.png)
- **Observaciones:** La eliminación es inmediata y no solicita confirmación. Solo se eliminó un cliente creado durante la exploración.
- **Posible defecto:** HAL-11 (observación)

### C1. Inicio y cierre de sesión del cliente

#### EXP-029 — Inicio de sesión de un cliente existente

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar el ingreso del cliente y la información mostrada.
- **Precondiciones:** Pantalla Customer Login.
- **Datos de entrada:** Your Name: Harry Potter (cliente precargado, solo consulta).
- **Pasos:**
  1. Pulsar Customer Login.
  2. Seleccionar 'Harry Potter'.
  3. Pulsar Login.
- **Resultado esperado:** Se muestra la vista de la cuenta con el nombre, la cuenta, el saldo y la moneda.
- **Resultado observado:** Ruta #/account. Texto 'Welcome Harry Potter !!'. Cuentas disponibles 1004, 1005 y 1006; seleccionada 1004 con Balance 0 y Currency Dollar. Botones Home, Logout, Transactions, Deposit y Withdrawl.
- **Estado:** Conforme
- **Evidencias:** [EXP-029_login_cliente_seleccion.png](../02_Capturas_Anotadas/EXP-029_login_cliente_seleccion.png), [EXP-029_login_cliente_exitoso.png](../02_Capturas_Anotadas/EXP-029_login_cliente_exitoso.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-030 — Cambio entre las cuentas del cliente

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el cambio de cuenta y la actualización de saldo y moneda.
- **Precondiciones:** Sesión iniciada como Harry Potter (EXP-029).
- **Datos de entrada:** Cuentas 1005 y 1006.
- **Pasos:**
  1. Seleccionar la cuenta 1005.
  2. Seleccionar la cuenta 1006.
- **Resultado esperado:** Se actualizan el número de cuenta, el saldo y la moneda.
- **Resultado observado:** 1005: Balance 0, Currency Pound. 1006: Balance 0, Currency Rupee.
- **Estado:** Conforme
- **Evidencias:** [EXP-030_cambio_cuenta_1005.png](../02_Capturas_Anotadas/EXP-030_cambio_cuenta_1005.png), [EXP-030_cambio_cuenta_1006.png](../02_Capturas_Anotadas/EXP-030_cambio_cuenta_1006.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-031 — Ingreso de un cliente sin cuentas

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar la vista de un cliente que aún no tiene cuentas.
- **Precondiciones:** Cliente de prueba Sofia QASinCuenta (QA0004) creado sin cuentas.
- **Datos de entrada:** Your Name: Sofia QASinCuenta.
- **Pasos:**
  1. Pulsar Customer Login.
  2. Seleccionar 'Sofia QASinCuenta' y pulsar Login.
- **Resultado esperado:** Se informa que el cliente no tiene cuentas y no se habilitan operaciones.
- **Resultado observado:** Texto 'Welcome Sofia QASinCuenta !! Please open an account with us.' No se muestran las pestañas Transactions, Deposit ni Withdrawl.
- **Estado:** Conforme
- **Evidencias:** [EXP-031_cliente_sin_cuentas.png](../02_Capturas_Anotadas/EXP-031_cliente_sin_cuentas.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-032 — Cerrar sesión del cliente

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar el cierre de sesión.
- **Precondiciones:** Sesión iniciada como Harry Potter.
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Pulsar Logout.
- **Resultado esperado:** Se cierra la sesión y se regresa a la selección de cliente.
- **Resultado observado:** Ruta #/customer, selector en '---Your Name---', botón Logout oculto y botón Login oculto.
- **Estado:** Conforme
- **Evidencias:** [EXP-032_logout_antes.png](../02_Capturas_Anotadas/EXP-032_logout_antes.png), [EXP-032_logout_despues.png](../02_Capturas_Anotadas/EXP-032_logout_despues.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-033 — Comportamiento después de cerrar sesión (botón Atrás)

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar que tras el Logout no se pueda volver a la cuenta.
- **Precondiciones:** Logout realizado (EXP-032).
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Tras el Logout, pulsar el botón Atrás del navegador.
- **Resultado esperado:** No se muestra la información de la cuenta sin un nuevo inicio de sesión.
- **Resultado observado:** El navegador vuelve a #/account y muestra 'Welcome Harry Potter !!', las cuentas 1004 1005 1006, 'Account Number : 1006 , Balance : 0 , Currency : Rupee', el botón Logout y las pestañas de operación.
- **Estado:** No conforme
- **Evidencias:** [EXP-033_atras_tras_logout.png](../02_Capturas_Anotadas/EXP-033_atras_tras_logout.png)
- **Observaciones:** No se verificó si desde esa vista pueden ejecutarse operaciones; se recomienda incluir esa verificación en las pruebas automatizadas.
- **Posible defecto:** HAL-02

#### EXP-034 — Uso del botón Home durante la sesión del cliente

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el efecto de Home sobre la sesión del cliente.
- **Precondiciones:** Sesión iniciada como Harry Potter.
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Pulsar Home.
  2. Pulsar Customer Login.
- **Resultado esperado:** Home regresa a la pantalla principal.
- **Resultado observado:** Home lleva a #/login sin botón Logout. Al pulsar Customer Login el selector aparece en '---Your Name---' (sin cliente seleccionado).
- **Estado:** Conforme
- **Evidencias:** [EXP-034_home_cliente.png](../02_Capturas_Anotadas/EXP-034_home_cliente.png)
- **Observaciones:** Home se comporta en la práctica como una salida de la sesión del cliente.
- **Posible defecto:** No aplica

### C2. Depósitos (Deposit)

#### EXP-035 — Depósito válido

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar el depósito y la actualización del saldo.
- **Precondiciones:** Sesión de navegador con el cliente de prueba Ana QAPrueba (Post Code QA0001) creado por el administrador y con las cuentas 1016 (Dollar) y 1017 (Pound); ingreso mediante Customer Login con la cuenta 1016 seleccionada.
- **Datos de entrada:** Monto: 1000. Saldo inicial: 0 (Dollar).
- **Pasos:**
  1. Pulsar la pestaña Deposit.
  2. Escribir 1000 en 'Amount to be Deposited'.
  3. Pulsar Deposit.
- **Resultado esperado:** Mensaje de éxito y saldo incrementado en 1000.
- **Resultado observado:** Mensaje 'Deposit Successful'. Saldo 0 → 1000. El campo de monto queda vacío.
- **Estado:** Conforme
- **Evidencias:** [EXP-035_deposito_valido_antes.png](../02_Capturas_Anotadas/EXP-035_deposito_valido_antes.png), [EXP-035_deposito_valido_despues.png](../02_Capturas_Anotadas/EXP-035_deposito_valido_despues.png)
- **Observaciones:** Comprobación: 0 + 1000 = 1000.
- **Posible defecto:** No aplica

#### EXP-036 — Depósitos consecutivos

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar que varios depósitos seguidos se acumulan correctamente.
- **Precondiciones:** Saldo 1000 en la cuenta 1016 (EXP-035).
- **Datos de entrada:** Montos: 500 y 250.
- **Pasos:**
  1. Depositar 500.
  2. Depositar 250.
- **Resultado esperado:** El saldo acumula los depósitos.
- **Resultado observado:** 500: 'Deposit Successful', saldo 1000 → 1500. 250: 'Deposit Successful', saldo 1500 → 1750.
- **Estado:** Conforme
- **Evidencias:** [EXP-036_deposito_consecutivo_1_antes.png](../02_Capturas_Anotadas/EXP-036_deposito_consecutivo_1_antes.png), [EXP-036_deposito_consecutivo_1_despues.png](../02_Capturas_Anotadas/EXP-036_deposito_consecutivo_1_despues.png), [EXP-036_deposito_consecutivo_2_antes.png](../02_Capturas_Anotadas/EXP-036_deposito_consecutivo_2_antes.png), [EXP-036_deposito_consecutivo_2_despues.png](../02_Capturas_Anotadas/EXP-036_deposito_consecutivo_2_despues.png)
- **Observaciones:** Comprobación: 1000 + 500 + 250 = 1750.
- **Posible defecto:** No aplica

#### EXP-037 — Depósito de cero

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el rechazo de depósitos con monto cero.
- **Precondiciones:** Saldo 1750 en la cuenta 1016.
- **Datos de entrada:** Monto: 0 (dos variantes).
- **Pasos:**
  1. Variante A: inmediatamente después del depósito exitoso de EXP-036, escribir 0 y pulsar Deposit.
  2. Variante B: abrir de nuevo el formulario Deposit, escribir 0 y pulsar Deposit.
- **Resultado esperado:** El depósito se rechaza con un mensaje de error y el saldo no cambia.
- **Resultado observado:** El saldo permanece en 1750 en ambas variantes. Variante A: sigue visible el mensaje anterior 'Deposit Successful'. Variante B: no aparece ningún mensaje. En ambas la consola registra 'TypeError: Cannot read properties of undefined (reading 'success')' en depositController.js y el valor 0 permanece en el campo.
- **Estado:** No conforme
- **Evidencias:** [EXP-037_deposito_cero_tras_exito_antes.png](../02_Capturas_Anotadas/EXP-037_deposito_cero_tras_exito_antes.png), [EXP-037_deposito_cero_tras_exito_despues.png](../02_Capturas_Anotadas/EXP-037_deposito_cero_tras_exito_despues.png), [EXP-037_deposito_cero_antes.png](../02_Capturas_Anotadas/EXP-037_deposito_cero_antes.png), [EXP-037_deposito_cero_despues.png](../02_Capturas_Anotadas/EXP-037_deposito_cero_despues.png)
- **Observaciones:** El saldo queda protegido, pero el usuario no recibe retroalimentación y en la variante A se muestra un mensaje de éxito engañoso.
- **Posible defecto:** HAL-03, HAL-04

#### EXP-038 — Depósito negativo

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el rechazo de montos negativos.
- **Precondiciones:** Saldo 1750 en la cuenta 1016.
- **Datos de entrada:** Monto: -100.
- **Pasos:**
  1. Abrir Deposit.
  2. Escribir -100.
  3. Pulsar Deposit.
- **Resultado esperado:** El depósito se rechaza con un mensaje de error y el saldo no cambia.
- **Resultado observado:** Sin mensaje visible. Saldo 1750 sin cambios. El valor -100 permanece en el campo. Error en consola: 'TypeError: Cannot read properties of undefined (reading 'success')'.
- **Estado:** No conforme
- **Evidencias:** [EXP-038_deposito_negativo_antes.png](../02_Capturas_Anotadas/EXP-038_deposito_negativo_antes.png), [EXP-038_deposito_negativo_despues.png](../02_Capturas_Anotadas/EXP-038_deposito_negativo_despues.png)
- **Observaciones:** El campo numérico no tiene restricción de mínimo (min).
- **Posible defecto:** HAL-03

#### EXP-039 — Depósito con el campo vacío

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar la validación del monto obligatorio.
- **Precondiciones:** Saldo 1750 en la cuenta 1016.
- **Datos de entrada:** Monto vacío.
- **Pasos:**
  1. Abrir Deposit.
  2. Sin escribir monto, pulsar Deposit.
- **Resultado esperado:** Se solicita diligenciar el monto y el saldo no cambia.
- **Resultado observado:** Validación nativa 'Completa este campo'. Saldo 1750 sin cambios.
- **Estado:** Conforme
- **Evidencias:** [EXP-039_deposito_vacio.png](../02_Capturas_Anotadas/EXP-039_deposito_vacio.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-040 — Depósito con valor decimal

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Determinar si se admiten montos con decimales.
- **Precondiciones:** Saldo 1750 en la cuenta 1016.
- **Datos de entrada:** Monto: 10.5.
- **Pasos:**
  1. Abrir Deposit.
  2. Escribir 10.5.
  3. Pulsar Deposit.
- **Resultado esperado:** Comportamiento por determinar (no existe especificación sobre decimales).
- **Resultado observado:** Validación nativa: 'Introduce un valor válido. Los dos valores válidos más aproximados son 10 y 11.' Saldo 1750 sin cambios.
- **Estado:** Conforme
- **Evidencias:** [EXP-040_deposito_decimal.png](../02_Capturas_Anotadas/EXP-040_deposito_decimal.png)
- **Observaciones:** El campo solo admite enteros (paso 1 del input numérico): no es posible depositar centavos.
- **Posible defecto:** HAL-15 (observación)

#### EXP-041 — Depósito con caracteres no numéricos

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el tratamiento de texto y notación especial en el monto.
- **Precondiciones:** Saldo 1750 en la cuenta 1016.
- **Datos de entrada:** Texto 'abc'; luego '1e2'.
- **Pasos:**
  1. Abrir Deposit y teclear 'abc'.
  2. Pulsar Deposit.
  3. Abrir de nuevo Deposit, teclear '1e2' y pulsar Deposit.
- **Resultado esperado:** No se aceptan caracteres no numéricos.
- **Resultado observado:** Al teclear 'abc' el campo queda vacío y aparece 'Completa este campo'; el saldo no cambia. Al teclear '1e2' el campo lo acepta, aparece 'Deposit Successful' y el saldo pasa de 1750 a 1850 (se interpreta como 100).
- **Estado:** Conforme
- **Evidencias:** [EXP-041_deposito_no_numerico.png](../02_Capturas_Anotadas/EXP-041_deposito_no_numerico.png), [EXP-041_deposito_notacion_e_antes.png](../02_Capturas_Anotadas/EXP-041_deposito_notacion_e_antes.png), [EXP-041_deposito_notacion_e_despues.png](../02_Capturas_Anotadas/EXP-041_deposito_notacion_e_despues.png)
- **Observaciones:** El campo numérico acepta notación exponencial; la automatización no debe asumir que solo se aceptan dígitos.
- **Posible defecto:** HAL-15 (observación)

### C3. Retiros (Withdrawl)

#### EXP-042 — Retiro válido

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar el retiro y la actualización del saldo.
- **Precondiciones:** Saldo 1850 en la cuenta 1016.
- **Datos de entrada:** Monto: 300.
- **Pasos:**
  1. Pulsar Withdrawl.
  2. Escribir 300 en 'Amount to be Withdrawn'.
  3. Pulsar Withdraw.
- **Resultado esperado:** Mensaje de éxito y saldo reducido en 300.
- **Resultado observado:** Mensaje 'Transaction successful'. Saldo 1850 → 1550.
- **Estado:** Conforme
- **Evidencias:** [EXP-042_retiro_valido_antes.png](../02_Capturas_Anotadas/EXP-042_retiro_valido_antes.png), [EXP-042_retiro_valido_despues.png](../02_Capturas_Anotadas/EXP-042_retiro_valido_despues.png)
- **Observaciones:** Comprobación: 1850 − 300 = 1550. Si se escribe el monto antes de que termine de cargar la vista de retiro, el valor se pierde; la automatización debe esperar la etiqueta 'Amount to be Withdrawn'.
- **Posible defecto:** No aplica

#### EXP-043 — Retiro superior al saldo

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el rechazo de retiros mayores al saldo disponible.
- **Precondiciones:** Saldo 1550 en la cuenta 1016.
- **Datos de entrada:** Monto: 2550.
- **Pasos:**
  1. Abrir Withdrawl.
  2. Escribir 2550.
  3. Pulsar Withdraw.
- **Resultado esperado:** El retiro se rechaza con un mensaje y el saldo no cambia.
- **Resultado observado:** Mensaje 'Transaction Failed. You can not withdraw amount more than the balance.' Saldo 1550 sin cambios. La consola registra 'Can not perform this transaction'.
- **Estado:** Conforme
- **Evidencias:** [EXP-043_retiro_saldo_insuficiente_antes.png](../02_Capturas_Anotadas/EXP-043_retiro_saldo_insuficiente_antes.png), [EXP-043_retiro_saldo_insuficiente_despues.png](../02_Capturas_Anotadas/EXP-043_retiro_saldo_insuficiente_despues.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-044 — Retiro del saldo total exacto

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el valor límite: retirar todo el saldo y luego intentar retirar con saldo cero.
- **Precondiciones:** Saldo 1550 en la cuenta 1016.
- **Datos de entrada:** Montos: 1550 y luego 1.
- **Pasos:**
  1. Retirar 1550.
  2. Retirar 1 con saldo 0.
- **Resultado esperado:** El retiro del total se acepta y deja saldo 0; el siguiente retiro se rechaza.
- **Resultado observado:** 1550: 'Transaction successful', saldo 0. 1: 'Transaction Failed. You can not withdraw amount more than the balance.', saldo 0.
- **Estado:** Conforme
- **Evidencias:** [EXP-044_retiro_saldo_total_antes.png](../02_Capturas_Anotadas/EXP-044_retiro_saldo_total_antes.png), [EXP-044_retiro_saldo_total_despues.png](../02_Capturas_Anotadas/EXP-044_retiro_saldo_total_despues.png), [EXP-044_retiro_con_saldo_cero_antes.png](../02_Capturas_Anotadas/EXP-044_retiro_con_saldo_cero_antes.png), [EXP-044_retiro_con_saldo_cero_despues.png](../02_Capturas_Anotadas/EXP-044_retiro_con_saldo_cero_despues.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-045 — Retiro de cero

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el rechazo de retiros con monto cero.
- **Precondiciones:** Saldo 1550 en la cuenta 1016 (antes de EXP-044).
- **Datos de entrada:** Monto: 0.
- **Pasos:**
  1. Abrir Withdrawl.
  2. Escribir 0.
  3. Pulsar Withdraw.
- **Resultado esperado:** El retiro se rechaza con un mensaje y el saldo no cambia.
- **Resultado observado:** Sin mensaje visible. Saldo 1550 sin cambios. Valor 0 permanece en el campo. Error en consola: 'TypeError: Cannot read properties of undefined (reading 'success')' en withdrawlController.js.
- **Estado:** No conforme
- **Evidencias:** [EXP-045_retiro_cero_antes.png](../02_Capturas_Anotadas/EXP-045_retiro_cero_antes.png), [EXP-045_retiro_cero_despues.png](../02_Capturas_Anotadas/EXP-045_retiro_cero_despues.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** HAL-05

#### EXP-046 — Retiro negativo

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el rechazo de montos negativos en retiros.
- **Precondiciones:** Saldo 1550 en la cuenta 1016.
- **Datos de entrada:** Monto: -50.
- **Pasos:**
  1. Abrir Withdrawl.
  2. Escribir -50.
  3. Pulsar Withdraw.
- **Resultado esperado:** El retiro se rechaza con un mensaje y el saldo no cambia.
- **Resultado observado:** Sin mensaje visible. Saldo 1550 sin cambios (no se incrementó). Error en consola 'TypeError: Cannot read properties of undefined (reading 'success')'.
- **Estado:** No conforme
- **Evidencias:** [EXP-046_retiro_negativo_antes.png](../02_Capturas_Anotadas/EXP-046_retiro_negativo_antes.png), [EXP-046_retiro_negativo_despues.png](../02_Capturas_Anotadas/EXP-046_retiro_negativo_despues.png)
- **Observaciones:** Se comprobó que el retiro negativo no incrementa el saldo.
- **Posible defecto:** HAL-05

#### EXP-047 — Retiro con el campo vacío

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar la validación del monto obligatorio en retiros.
- **Precondiciones:** Saldo 1550 en la cuenta 1016.
- **Datos de entrada:** Monto vacío.
- **Pasos:**
  1. Abrir Withdrawl.
  2. Sin escribir monto, pulsar Withdraw.
- **Resultado esperado:** Se solicita diligenciar el monto y el saldo no cambia.
- **Resultado observado:** Validación nativa 'Completa este campo'. Saldo 1550 sin cambios.
- **Estado:** Conforme
- **Evidencias:** [EXP-047_retiro_vacio.png](../02_Capturas_Anotadas/EXP-047_retiro_vacio.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** No aplica

#### EXP-048 — Retiro con valores inválidos (decimal y no numérico)

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el tratamiento de decimales y texto en retiros.
- **Precondiciones:** Saldo 1550 en la cuenta 1016.
- **Datos de entrada:** Monto 5.5; luego texto 'xyz'.
- **Pasos:**
  1. Abrir Withdrawl, escribir 5.5 y pulsar Withdraw.
  2. Abrir de nuevo Withdrawl, teclear 'xyz' y pulsar Withdraw.
- **Resultado esperado:** No se procesan valores inválidos y el saldo no cambia.
- **Resultado observado:** 5.5: 'Introduce un valor válido. Los dos valores válidos más aproximados son 5 y 6.' 'xyz': el campo queda vacío y aparece 'Completa este campo'. Saldo 1550 sin cambios en ambos casos.
- **Estado:** Conforme
- **Evidencias:** [EXP-048_retiro_decimal.png](../02_Capturas_Anotadas/EXP-048_retiro_decimal.png), [EXP-048_retiro_no_numerico.png](../02_Capturas_Anotadas/EXP-048_retiro_no_numerico.png)
- **Observaciones:** Ninguna.
- **Posible defecto:** HAL-15 (observación)

### C4. Movimientos (Transactions)

#### EXP-049 — Consultar el historial de movimientos

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Exitoso
- **Objetivo:** Verificar que los depósitos y retiros aparecen en Transactions.
- **Precondiciones:** Operaciones EXP-035 a EXP-048 realizadas en la cuenta 1016.
- **Datos de entrada:** Cuenta 1016.
- **Pasos:**
  1. Pulsar Transactions.
  2. Revisar las filas, importes, fechas y tipos.
- **Resultado esperado:** Se listan todas las operaciones exitosas con fecha, monto y tipo.
- **Resultado observado:** Ruta #/listTx. Seis movimientos: 1000 Credit (6:54:48 PM), 500 Credit (6:54:51 PM), 250 Credit (6:54:55 PM), 100 Credit (6:55:16 PM), 300 Debit (6:56:05 PM) y 1550 Debit (6:56:27 PM), todos del 14 de noviembre de 2026. Se muestran los filtros de fecha (inicializados con la primera y la última transacción), Back y Reset.
- **Estado:** Conforme
- **Evidencias:** [EXP-049_historial_movimientos.png](../02_Capturas_Anotadas/EXP-049_historial_movimientos.png)
- **Observaciones:** Las operaciones rechazadas (cero, negativos, saldo insuficiente) no generan movimientos. Depósitos = Credit; retiros = Debit.
- **Posible defecto:** No aplica

#### EXP-050 — Consultar movimientos de una cuenta sin transacciones

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar la vista de movimientos de una cuenta nueva.
- **Precondiciones:** Cuenta 1017 (Pound) sin operaciones.
- **Datos de entrada:** Cuenta 1017.
- **Pasos:**
  1. Seleccionar la cuenta 1017.
  2. Pulsar Transactions.
- **Resultado esperado:** La tabla aparece vacía.
- **Resultado observado:** Tabla sin filas. No se muestran los filtros de fecha ni el botón Reset; solo Back y los encabezados.
- **Estado:** Conforme
- **Evidencias:** [EXP-050_cuenta_sin_movimientos.png](../02_Capturas_Anotadas/EXP-050_cuenta_sin_movimientos.png)
- **Observaciones:** No se presenta un mensaje de 'sin movimientos'.
- **Posible defecto:** No aplica

#### EXP-051 — Filtrar movimientos por rango de fechas

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el filtro por fecha y hora inicial y final.
- **Precondiciones:** Sesión independiente: cliente Ana QAPrueba con cuenta 1016, depósitos de 1000 (6:58:47 PM) y 500 (6:58:49 PM) y retiro de 200 (6:59:04 PM); vista abierta a las 7:00:07 PM.
- **Datos de entrada:** Variante A: inicio 2026-11-14 18:59, fin 2026-11-14 19:00. Variante B: inicio 18:59, fin 19:30.
- **Pasos:**
  1. Abrir Transactions y observar el rango por defecto.
  2. Variante A: escribir el rango dentro de los límites.
  3. Variante B: cambiar la fecha final a 30 minutos en el futuro.
- **Resultado esperado:** Se muestran solo los movimientos dentro del rango; ante un rango no permitido se informa al usuario.
- **Resultado observado:** Por defecto se listan los 3 movimientos. Variante A: solo aparece '200 | Debit' (6:59:04 PM). Variante B: la tabla queda vacía sin ningún mensaje; el campo final queda inválido (rangeOverflow, clase ng-invalid-max) porque su máximo es la hora de apertura de la vista (19:00:05).
- **Estado:** Conforme
- **Evidencias:** [EXP-051_filtro_sin_aplicar.png](../02_Capturas_Anotadas/EXP-051_filtro_sin_aplicar.png), [EXP-051_filtro_fechas.png](../02_Capturas_Anotadas/EXP-051_filtro_fechas.png), [EXP-051_filtro_fin_futuro.png](../02_Capturas_Anotadas/EXP-051_filtro_fin_futuro.png)
- **Observaciones:** El filtro funciona dentro de los límites (variante A). La variante B se registra como hallazgo.
- **Posible defecto:** HAL-09

#### EXP-052 — Filtro de fechas sin resultados

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Negativo
- **Objetivo:** Verificar el comportamiento ante un rango válido sin movimientos.
- **Precondiciones:** Sesión como Hermoine Granger, cuenta precargada 1001 (solo consulta).
- **Datos de entrada:** Inicio 2015-01-01 01:00; fin 2015-01-01 23:00.
- **Pasos:**
  1. Abrir Transactions de la cuenta 1001.
  2. Escribir el rango indicado.
- **Resultado esperado:** La tabla queda vacía.
- **Resultado observado:** Tabla sin filas. Ambos campos son válidos dentro de sus límites. No hay mensaje de 'sin resultados'.
- **Estado:** Conforme
- **Evidencias:** [EXP-052_filtro_sin_resultados.png](../02_Capturas_Anotadas/EXP-052_filtro_sin_resultados.png)
- **Observaciones:** Los movimientos precargados están registrados a las 12:00:00 AM de cada día.
- **Posible defecto:** No aplica

#### EXP-053 — Ordenar movimientos por Date-Time

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el ordenamiento por fecha.
- **Precondiciones:** Historial de la cuenta 1016 con 6 movimientos.
- **Datos de entrada:** Encabezado Date-Time.
- **Pasos:**
  1. Pulsar el encabezado Date-Time.
  2. Pulsarlo nuevamente.
- **Resultado esperado:** Los movimientos se ordenan por fecha y alternan el sentido.
- **Resultado observado:** Primer clic: orden descendente (6:56:27 PM primero). Segundo clic: orden ascendente. La URL cambia a '.../BankingProject/#'.
- **Estado:** Conforme
- **Evidencias:** [EXP-053_ordenar_fecha_1.png](../02_Capturas_Anotadas/EXP-053_ordenar_fecha_1.png)
- **Observaciones:** Las columnas Amount y Transaction Type no son ordenables.
- **Posible defecto:** HAL-12 (observación)

#### EXP-054 — Navegación con Back y controles de desplazamiento

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar los controles '<', 'Top', '>' y el botón Back con un historial extenso.
- **Precondiciones:** Sesión como Hermoine Granger, cuenta precargada 1001 (solo consulta).
- **Datos de entrada:** Cuenta 1001.
- **Pasos:**
  1. Abrir Transactions.
  2. Pulsar '>' dos veces.
  3. Pulsar Top.
  4. Aplicar el filtro 2015-01-01 00:00 a 2015-01-03 23:59.
  5. Pulsar Back.
- **Resultado esperado:** Los controles permiten recorrer el historial y Back regresa a la cuenta.
- **Resultado observado:** Cuenta 1001: saldo 5096 Dollar, 392 movimientos entre el 1 de enero y el 28 de julio de 2015, todos cargados en la tabla. Inicialmente solo se ve '>'. Tras pulsarlo, la URL pasa a #/listTx#anchor5 y luego #anchor13, la tabla se desplaza y aparecen '<' y 'Top'. Top vuelve al inicio (#anchor). El filtro devuelve 6 movimientos (30 Credit y 4 Debit por día). Back regresa a #/account con la cuenta 1001.
- **Estado:** Conforme
- **Evidencias:** [EXP-054_paginacion_inicio.png](../02_Capturas_Anotadas/EXP-054_paginacion_inicio.png), [EXP-054_paginacion_derecha.png](../02_Capturas_Anotadas/EXP-054_paginacion_derecha.png), [EXP-054_filtro_precargado.png](../02_Capturas_Anotadas/EXP-054_filtro_precargado.png), [EXP-054_back_despues.png](../02_Capturas_Anotadas/EXP-054_back_despues.png)
- **Observaciones:** No existe paginación real: los controles desplazan la tabla entre anclas.
- **Posible defecto:** No aplica

#### EXP-055 — Reiniciar movimientos con Reset

- **Actor:** Cliente bancario (Customer)
- **Tipo de flujo:** Alternativo
- **Objetivo:** Verificar el efecto de Reset sobre el historial y el saldo (solo cuenta de prueba).
- **Precondiciones:** Cuenta de prueba 1016 con saldo 0; evidencias de EXP-049 a EXP-053 ya capturadas.
- **Datos de entrada:** Depósito previo de 700.
- **Pasos:**
  1. Depositar 700 en la cuenta 1016.
  2. Abrir Transactions.
  3. Pulsar Reset.
  4. Pulsar Back y revisar el saldo.
- **Resultado esperado:** Se eliminan los movimientos de la cuenta (no hay especificación sobre el efecto en el saldo).
- **Resultado observado:** Antes de Reset: 7 movimientos y saldo 700. Reset actúa inmediatamente, sin confirmación: la tabla queda vacía. Al volver a la cuenta el saldo es 0.
- **Estado:** Conforme
- **Evidencias:** [EXP-055_reset_deposito_previo_antes.png](../02_Capturas_Anotadas/EXP-055_reset_deposito_previo_antes.png), [EXP-055_reset_deposito_previo_despues.png](../02_Capturas_Anotadas/EXP-055_reset_deposito_previo_despues.png), [EXP-055_reset_antes.png](../02_Capturas_Anotadas/EXP-055_reset_antes.png), [EXP-055_reset_despues.png](../02_Capturas_Anotadas/EXP-055_reset_despues.png), [EXP-055_reset_saldo.png](../02_Capturas_Anotadas/EXP-055_reset_saldo.png)
- **Observaciones:** Reset borra el historial y también deja el saldo en 0, sin solicitar confirmación. Se registra como comportamiento por confirmar.
- **Posible defecto:** HAL-10

### C5. Transferencias

#### EXP-056 — Verificar la existencia de la funcionalidad de transferencias

- **Actor:** Cliente bancario (Customer) / Administrador bancario (Bank Manager)
- **Tipo de flujo:** Negativo
- **Objetivo:** Determinar si la aplicación permite transferencias entre cuentas, solicitadas en el enunciado del taller.
- **Precondiciones:** Sesión de cliente y vista de administrador disponibles.
- **Datos de entrada:** No requiere datos.
- **Pasos:**
  1. Revisar las opciones de la vista del cliente.
  2. Revisar las opciones de la vista del administrador.
  3. Buscar el texto 'transfer' en ambas vistas.
- **Resultado esperado:** Según el enunciado, debería existir una opción de transferencia entre cuentas.
- **Resultado observado:** Cliente: solo Home, Logout, Transactions, Deposit y Withdrawl. Administrador: solo Home, Add Customer, Open Account y Customers. El texto 'transfer' no aparece en ninguna vista. No existe flujo de transferencia.
- **Estado:** Bloqueado
- **Evidencias:** [EXP-056_sin_transferencias_cliente.png](../02_Capturas_Anotadas/EXP-056_sin_transferencias_cliente.png), [EXP-056_sin_transferencias_admin.png](../02_Capturas_Anotadas/EXP-056_sin_transferencias_admin.png)
- **Observaciones:** No se simularon transferencias ni se confundieron con depósitos o retiros. Se documenta como diferencia entre el enunciado y la aplicación.
- **Posible defecto:** HAL-13 (limitación)
