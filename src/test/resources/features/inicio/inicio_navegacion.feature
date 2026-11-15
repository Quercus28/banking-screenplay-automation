# language: es
@inicio
Característica: Inicio y navegación en XYZ Bank
  Como usuario de XYZ Bank
  Quiero acceder a la aplicación y navegar entre sus módulos
  Para usar las funcionalidades de cliente o de administrador

  @EXP-001 @exitoso
  Escenario: Carga de la pantalla principal del banco
    Dado que el visitante abre la aplicación del banco
    Entonces el título de la página es "XYZ Bank"
    Y se muestran las opciones "Home", "Customer Login" y "Bank Manager Login"
    Y no se muestra el botón "Logout"

  @EXP-002 @exitoso @HAL-16
  Escenario: Ingreso como administrador
    Dado que el visitante abre la aplicación del banco
    Cuando ingresa como administrador
    Entonces la ruta de la aplicación es "#/manager"
    Y se muestran las opciones "Add Customer", "Open Account" y "Customers"

  @EXP-003 @negativo
  Escenario: Ingreso al módulo cliente sin seleccionar un nombre
    Dado que el visitante abre la aplicación del banco
    Cuando abre el inicio de sesión de cliente sin seleccionar un nombre
    Entonces el selector de clientes lista los clientes precargados
      | Hermoine Granger   |
      | Harry Potter       |
      | Ron Weasly         |
      | Albus Dumbledore   |
      | Neville Longbottom |
    Y no se muestra el botón "Login"
    Cuando selecciona el cliente "Harry Potter"
    Entonces se muestra el botón "Login"
    Cuando vuelve a la opción "---Your Name---"
    Entonces no se muestra el botón "Login"

  @EXP-004 @alternativo
  Escenario: Regreso a la pantalla principal con el botón Home
    Dado que el administrador está en la pestaña "Open Account"
    Cuando pulsa el botón "Home"
    Entonces la ruta de la aplicación es "#/login"
    Y se muestran las opciones "Customer Login" y "Bank Manager Login"
    Cuando regresa con el botón Atrás del navegador
    Entonces la ruta de la aplicación es "#/manager/openAccount"

  # Las tres rutas van en escenarios separados: en un esquema, Serenity acumula en la última fila
  # los fallos de las anteriores y el reporte no mostraría el resultado real de cada ruta.
  @EXP-005 @negativo @defecto @HAL-01
  Escenario: Acceso directo sin sesión a la ruta del administrador
    Dado que el visitante tiene una sesión nueva del navegador
    Cuando abre directamente la ruta "#/manager/addCust"
    Entonces es redirigido a la pantalla principal o recibe un mensaje de acceso no autorizado

  @EXP-005 @negativo @defecto @HAL-01
  Escenario: Acceso directo sin sesión a la ruta de la cuenta del cliente
    Dado que el visitante tiene una sesión nueva del navegador
    Cuando abre directamente la ruta "#/account"
    Entonces es redirigido a la pantalla principal o recibe un mensaje de acceso no autorizado
    Y no se muestran plantillas sin procesar como "{{user}}"

  @EXP-005 @negativo @defecto @HAL-01
  Escenario: Acceso directo sin sesión a la ruta de movimientos
    Dado que el visitante tiene una sesión nueva del navegador
    Cuando abre directamente la ruta "#/listTx"
    Entonces es redirigido a la pantalla principal o recibe un mensaje de acceso no autorizado

  @EXP-006 @alternativo
  Escenario: Persistencia de datos al recargar y aislamiento entre sesiones
    Dado que el administrador registró los clientes
      | nombre | apellido | codigo_postal |
      | Ana    | QAPrueba | QA0001        |
    Y está en la pestaña "Customers"
    Cuando recarga la página
    Y busca "QAPrueba" en el listado de clientes
    Entonces el listado muestra 1 cliente
    Cuando abre la aplicación en una sesión nueva del navegador como administrador
    Y busca "QAPrueba" en el listado de clientes
    Entonces el listado muestra 0 clientes
