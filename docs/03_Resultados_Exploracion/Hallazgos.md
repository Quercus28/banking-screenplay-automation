# Hallazgos — GlobalSQA Banking

Comportamientos observados durante la exploración del 14 de noviembre de 2026. 'Confirmado' indica que se reprodujo de forma consistente; 'Por confirmar' depende de reglas de negocio no documentadas.

| ID | Hallazgo | Clasificación | Impacto | Estado | Evidencia |
|---|---|---|---|---|---|
| HAL-01 | Rutas internas accesibles sin sesión; #/account muestra plantillas sin procesar | Posible defecto | Medio | Confirmado (reproducible) | EXP-005 |
| HAL-02 | Tras el Logout, el botón Atrás del navegador vuelve a mostrar la cuenta | Posible defecto | Alto en un contexto bancario real (exposición de información); medio en la aplicación de demostración | Confirmado (reproducible) | EXP-033 |
| HAL-03 | Depósitos de 0 o negativos se rechazan sin mensaje y generan un error JavaScript | Posible defecto | Medio | Confirmado (reproducible) | EXP-037, EXP-038 |
| HAL-04 | El mensaje de éxito anterior permanece tras un depósito rechazado | Posible defecto | Medio (información engañosa) | Confirmado (reproducible) | EXP-037 (variante A) |
| HAL-05 | Retiros de 0 o negativos se rechazan sin mensaje y generan un error JavaScript | Posible defecto | Medio | Confirmado (reproducible) | EXP-045, EXP-046 |
| HAL-06 | Mensaje engañoso ante campos con solo espacios en Add Customer | Posible defecto | Bajo | Confirmado (reproducible) | EXP-013 |
| HAL-07 | La detección de duplicados distingue mayúsculas y minúsculas | Posible defecto | Medio (calidad de datos) | Por confirmar (regla de negocio no documentada) | EXP-015 |
| HAL-08 | Sin validación de formato en nombre, apellido y código postal | Posible defecto | Bajo | Por confirmar (regla de negocio no documentada) | EXP-011, EXP-012 |
| HAL-09 | Filtro de fechas: fecha final posterior a la apertura de la vista vacía la tabla sin aviso | Posible defecto | Medio (puede ocultar movimientos) | Confirmado (reproducible) | EXP-051 (variante B) |
| HAL-10 | Reset elimina el historial y deja el saldo en 0 sin confirmación | Comportamiento inesperado | Medio | Por confirmar (puede ser el diseño de la demo) | EXP-055 |
| HAL-11 | Delete elimina clientes sin confirmación | Observación | Bajo | Observación de usabilidad | EXP-028 |
| HAL-12 | Los encabezados ordenables cambian la URL a '#' | Observación | Bajo | Observación | EXP-027, EXP-053 |
| HAL-13 | No existe la funcionalidad de transferencias | Limitación | Alto para el alcance del taller | Confirmado (limitación de la aplicación) | EXP-056 |
| HAL-14 | Errores ortográficos en la interfaz y en los datos precargados | Observación | Bajo (cosmético); relevante para localizadores por texto | Confirmado | EXP-029, EXP-056 |
| HAL-15 | Montos solo enteros y aceptación de notación exponencial | Observación | Bajo | Por confirmar (restricción de diseño) | EXP-040, EXP-041, EXP-048 |
| HAL-16 | El administrador no requiere credenciales y no tiene Logout | Observación | Bajo en la demo | Observación (característica de la demo) | EXP-002, EXP-005 |
| HAL-17 | Sincronización: la vista de operación se reemplaza después del clic | Riesgo de automatización | Alto para la estabilidad de las pruebas automatizadas | Observado durante la exploración | EXP-020, EXP-042 |

## HAL-01 — Rutas internas accesibles sin sesión; #/account muestra plantillas sin procesar

- **Descripción:** Al abrir directamente #/account en una sesión nueva se muestran las expresiones AngularJS sin evaluar ({{user}}, {{accountNo}}, {{amount}}, {{currency}}), el botón Logout y las pestañas de operación. #/manager/addCust y #/listTx también se abren sin control de acceso.
- **Pasos para reproducir:**
  1. Abrir una ventana nueva del navegador.
  2. Ir directamente a https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account.
- **Resultado esperado:** Redirección a la pantalla principal o mensaje controlado.
- **Resultado obtenido:** Vista de cuenta con plantillas sin procesar y botón Logout.
- **Evidencia:** EXP-005
- **Impacto funcional estimado:** Medio
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-02 — Tras el Logout, el botón Atrás del navegador vuelve a mostrar la cuenta

- **Descripción:** Después de cerrar sesión, el botón Atrás del navegador regresa a #/account y muestra el nombre del cliente, las cuentas, el saldo y las opciones de operación.
- **Pasos para reproducir:**
  1. Ingresar como Harry Potter.
  2. Pulsar Logout.
  3. Pulsar el botón Atrás del navegador.
- **Resultado esperado:** La cuenta no se muestra sin un nuevo inicio de sesión.
- **Resultado obtenido:** Se muestra 'Welcome Harry Potter !!' con la cuenta 1006, saldo 0 y Logout.
- **Evidencia:** EXP-033
- **Impacto funcional estimado:** Alto en un contexto bancario real (exposición de información); medio en la aplicación de demostración
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-03 — Depósitos de 0 o negativos se rechazan sin mensaje y generan un error JavaScript

- **Descripción:** Al depositar 0 o -100 el saldo no cambia, pero no se informa al usuario y la consola registra 'TypeError: Cannot read properties of undefined (reading 'success')' en depositController.js.
- **Pasos para reproducir:**
  1. Ingresar como cliente con cuenta.
  2. Abrir Deposit.
  3. Escribir 0 (o -100) y pulsar Deposit.
- **Resultado esperado:** Mensaje de error indicando que el monto debe ser mayor que cero.
- **Resultado obtenido:** Sin mensaje; saldo sin cambios; excepción en consola.
- **Evidencia:** EXP-037, EXP-038
- **Impacto funcional estimado:** Medio
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-04 — El mensaje de éxito anterior permanece tras un depósito rechazado

- **Descripción:** Si después de un depósito exitoso se intenta depositar 0 sin cambiar de pestaña, sigue visible 'Deposit Successful' aunque la operación no se realizó.
- **Pasos para reproducir:**
  1. Realizar un depósito válido.
  2. Sin cambiar de pestaña, escribir 0 y pulsar Deposit.
- **Resultado esperado:** El mensaje anterior se reemplaza por un mensaje de error.
- **Resultado obtenido:** Se mantiene 'Deposit Successful'; el saldo no cambia.
- **Evidencia:** EXP-037 (variante A)
- **Impacto funcional estimado:** Medio (información engañosa)
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-05 — Retiros de 0 o negativos se rechazan sin mensaje y generan un error JavaScript

- **Descripción:** Al retirar 0 o -50 el saldo no cambia, no se muestra mensaje y la consola registra 'TypeError: Cannot read properties of undefined (reading 'success')' en withdrawlController.js.
- **Pasos para reproducir:**
  1. Ingresar como cliente con saldo.
  2. Abrir Withdrawl.
  3. Escribir 0 (o -50) y pulsar Withdraw.
- **Resultado esperado:** Mensaje de error indicando que el monto es inválido.
- **Resultado obtenido:** Sin mensaje; saldo sin cambios; excepción en consola.
- **Evidencia:** EXP-045, EXP-046
- **Impacto funcional estimado:** Medio
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-06 — Mensaje engañoso ante campos con solo espacios en Add Customer

- **Descripción:** Con espacios en blanco en los tres campos, el sistema no crea el cliente pero informa 'Please check the details. Customer may be duplicate.'
- **Pasos para reproducir:**
  1. Ingresar como administrador.
  2. Abrir Add Customer.
  3. Escribir espacios en los tres campos y pulsar Add Customer.
- **Resultado esperado:** Mensaje que indique campos obligatorios o inválidos.
- **Resultado obtenido:** Mensaje de posible cliente duplicado.
- **Evidencia:** EXP-013
- **Impacto funcional estimado:** Bajo
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-07 — La detección de duplicados distingue mayúsculas y minúsculas

- **Descripción:** 'ana qaprueba QA0001' se registra como cliente nuevo aunque existe 'Ana QAPrueba QA0001'. Los selectores muestran entradas idénticas imposibles de diferenciar.
- **Pasos para reproducir:**
  1. Crear Ana / QAPrueba / QA0001.
  2. Crear ana / qaprueba / QA0001.
- **Resultado esperado:** Se detecta el duplicado.
- **Resultado obtenido:** Se crea el cliente con id 11.
- **Evidencia:** EXP-015
- **Impacto funcional estimado:** Medio (calidad de datos)
- **Estado de confirmación:** Por confirmar (regla de negocio no documentada)
- **Clasificación:** Posible defecto

## HAL-08 — Sin validación de formato en nombre, apellido y código postal

- **Descripción:** Se aceptan nombre y apellido numéricos (12345 / 67890) y un código postal compuesto solo por símbolos (@#$%&*).
- **Pasos para reproducir:**
  1. Crear un cliente con First Name 12345, Last Name 67890 y Post Code 00000.
  2. Crear un cliente con Post Code @#$%&*.
- **Resultado esperado:** Validación del formato de los datos.
- **Resultado obtenido:** Los registros se crean (ids 8 y 9).
- **Evidencia:** EXP-011, EXP-012
- **Impacto funcional estimado:** Bajo
- **Estado de confirmación:** Por confirmar (regla de negocio no documentada)
- **Clasificación:** Posible defecto

## HAL-09 — Filtro de fechas: fecha final posterior a la apertura de la vista vacía la tabla sin aviso

- **Descripción:** La fecha final tiene como máximo el momento en que se abrió la vista de movimientos. Si se elige una fecha posterior, el campo queda inválido y la tabla se vacía sin mensaje.
- **Pasos para reproducir:**
  1. Abrir Transactions de una cuenta con movimientos.
  2. Establecer una fecha final posterior a la hora actual.
- **Resultado esperado:** Se muestran los movimientos del rango o un mensaje de validación.
- **Resultado obtenido:** Tabla vacía sin mensaje (rangeOverflow, ng-invalid-max).
- **Evidencia:** EXP-051 (variante B)
- **Impacto funcional estimado:** Medio (puede ocultar movimientos)
- **Estado de confirmación:** Confirmado (reproducible)
- **Clasificación:** Posible defecto

## HAL-10 — Reset elimina el historial y deja el saldo en 0 sin confirmación

- **Descripción:** El botón Reset de Transactions borra todos los movimientos y, al volver a la cuenta, el saldo es 0. No solicita confirmación.
- **Pasos para reproducir:**
  1. Depositar 700 en una cuenta de prueba.
  2. Abrir Transactions y pulsar Reset.
  3. Pulsar Back.
- **Resultado esperado:** No especificado; se esperaría al menos una confirmación ante una acción irreversible.
- **Resultado obtenido:** Historial vacío y saldo 0.
- **Evidencia:** EXP-055
- **Impacto funcional estimado:** Medio
- **Estado de confirmación:** Por confirmar (puede ser el diseño de la demo)
- **Clasificación:** Comportamiento inesperado

## HAL-11 — Delete elimina clientes sin confirmación

- **Descripción:** El botón Delete de Customers elimina el cliente inmediatamente.
- **Pasos para reproducir:**
  1. Abrir Customers.
  2. Pulsar Delete en un cliente de prueba.
- **Resultado esperado:** Confirmación antes de eliminar.
- **Resultado obtenido:** Eliminación inmediata.
- **Evidencia:** EXP-028
- **Impacto funcional estimado:** Bajo
- **Estado de confirmación:** Observación de usabilidad
- **Clasificación:** Observación

## HAL-12 — Los encabezados ordenables cambian la URL a '#'

- **Descripción:** Al pulsar First Name, Last Name, Post Code o Date-Time la URL pasa a '.../BankingProject/#'. El primer clic ordena de forma descendente.
- **Pasos para reproducir:**
  1. Abrir Customers o Transactions.
  2. Pulsar un encabezado ordenable y observar la URL.
- **Resultado esperado:** La ruta actual se conserva.
- **Resultado obtenido:** La URL cambia a '#'; la vista se mantiene.
- **Evidencia:** EXP-027, EXP-053
- **Impacto funcional estimado:** Bajo
- **Estado de confirmación:** Observación
- **Clasificación:** Observación

## HAL-13 — No existe la funcionalidad de transferencias

- **Descripción:** Ningún rol dispone de una opción de transferencia entre cuentas; el enunciado del taller solicita automatizarla.
- **Pasos para reproducir:**
  1. Revisar las vistas del cliente y del administrador.
- **Resultado esperado:** Opción de transferencia (según el enunciado).
- **Resultado obtenido:** Solo Deposit, Withdrawl y Transactions (cliente).
- **Evidencia:** EXP-056
- **Impacto funcional estimado:** Alto para el alcance del taller
- **Estado de confirmación:** Confirmado (limitación de la aplicación)
- **Clasificación:** Limitación

## HAL-14 — Errores ortográficos en la interfaz y en los datos precargados

- **Descripción:** La pestaña de retiro se rotula 'Withdrawl' (en lugar de 'Withdrawal'); los datos de demostración incluyen 'Hermoine' y 'Weasly'.
- **Pasos para reproducir:**
  1. Ingresar como cliente con cuenta.
  2. Observar las pestañas.
- **Resultado esperado:** Textos sin errores ortográficos.
- **Resultado obtenido:** 'Withdrawl'.
- **Evidencia:** EXP-029, EXP-056
- **Impacto funcional estimado:** Bajo (cosmético); relevante para localizadores por texto
- **Estado de confirmación:** Confirmado
- **Clasificación:** Observación

## HAL-15 — Montos solo enteros y aceptación de notación exponencial

- **Descripción:** El campo de monto rechaza decimales (10.5, 5.5) por la validación nativa, por lo que no es posible operar con centavos; a la vez acepta '1e2' como 100.
- **Pasos para reproducir:**
  1. Depositar 10.5.
  2. Depositar 1e2.
- **Resultado esperado:** No especificado.
- **Resultado obtenido:** 10.5 rechazado; 1e2 aceptado como 100.
- **Evidencia:** EXP-040, EXP-041, EXP-048
- **Impacto funcional estimado:** Bajo
- **Estado de confirmación:** Por confirmar (restricción de diseño)
- **Clasificación:** Observación

## HAL-16 — El administrador no requiere credenciales y no tiene Logout

- **Descripción:** Bank Manager Login da acceso inmediato sin autenticación y la vista del administrador no muestra Logout.
- **Pasos para reproducir:**
  1. Pulsar Bank Manager Login.
- **Resultado esperado:** No especificado (aplicación de demostración).
- **Resultado obtenido:** Acceso directo sin credenciales.
- **Evidencia:** EXP-002, EXP-005
- **Impacto funcional estimado:** Bajo en la demo
- **Estado de confirmación:** Observación (característica de la demo)
- **Clasificación:** Observación

## HAL-17 — Sincronización: la vista de operación se reemplaza después del clic

- **Descripción:** Al cambiar de Deposit a Withdrawl, si se escribe el monto antes de que cargue el nuevo formulario, el valor se pierde y la operación no se ejecuta. También, al volver a pulsar la pestaña activa, el formulario conserva valores y mensajes anteriores.
- **Pasos para reproducir:**
  1. Realizar un depósito.
  2. Pulsar Withdrawl y escribir el monto de inmediato.
- **Resultado esperado:** No aplica (riesgo para la automatización).
- **Resultado obtenido:** El monto se escribió en el formulario anterior.
- **Evidencia:** EXP-020, EXP-042
- **Impacto funcional estimado:** Alto para la estabilidad de las pruebas automatizadas
- **Estado de confirmación:** Observado durante la exploración
- **Clasificación:** Riesgo de automatización
