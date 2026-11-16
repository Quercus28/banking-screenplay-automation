# language: es
@customer @HU-05
Característica: HU-05 - Ingresar como cliente
  Como cliente,
  quiero ingresar a la banca seleccionando mi nombre,
  para consultar mis cuentas y hacer operaciones sobre ellas.

  @CA-HU05-01 @EXP-029 @exitoso
  Escenario: Inicio de sesión de un cliente existente
    Dado que el visitante abre la aplicación del banco
    Cuando inicia sesión como el cliente "Harry Potter"
    Entonces la ruta de la aplicación es "#/account"
    Y se muestra el mensaje de bienvenida "Welcome Harry Potter !!"
    Y el selector de cuentas ofrece las cuentas "1004", "1005" y "1006"
    Y la cuenta "1004" muestra saldo 0 y moneda "Dollar"
    Y se muestran las opciones "Logout", "Transactions", "Deposit" y "Withdrawl"

  @CA-HU05-02 @EXP-003 @negativo
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

  @CA-HU05-03 @EXP-031 @alternativo
  Escenario: Ingreso de un cliente sin cuentas
    Dado que el administrador registró los clientes
      | nombre | apellido    | codigo_postal |
      | Sofia  | QASinCuenta | QA0004        |
    Cuando inicia sesión como el cliente "Sofia QASinCuenta"
    Entonces se muestra el mensaje de bienvenida "Welcome Sofia QASinCuenta !!"
    Y se muestra el mensaje "Please open an account with us."
    Y no se muestran las opciones "Transactions", "Deposit" y "Withdrawl"

  @EXP-034 @alternativo
  Escenario: Uso del botón Home durante la sesión del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando pulsa el botón "Home"
    Entonces la ruta de la aplicación es "#/login"
    Y no se muestra el botón "Logout"
    Cuando abre el inicio de sesión de cliente sin seleccionar un nombre
    Entonces el selector de clientes muestra la opción "---Your Name---"
