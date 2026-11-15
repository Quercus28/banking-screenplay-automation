# language: es
@cliente @sesion_cliente
Característica: Inicio y cierre de sesión del cliente
  Como cliente bancario
  Quiero iniciar y cerrar sesión en XYZ Bank
  Para consultar y operar mis cuentas de forma segura

  @EXP-029 @exitoso
  Escenario: Inicio de sesión de un cliente existente
    Dado que el visitante abre la aplicación del banco
    Cuando inicia sesión como el cliente "Harry Potter"
    Entonces la ruta de la aplicación es "#/account"
    Y se muestra el mensaje de bienvenida "Welcome Harry Potter !!"
    Y el selector de cuentas ofrece las cuentas "1004", "1005" y "1006"
    Y la cuenta "1004" muestra saldo 0 y moneda "Dollar"
    Y se muestran las opciones "Logout", "Transactions", "Deposit" y "Withdrawl"

  @EXP-030 @alternativo
  Esquema del escenario: Cambio entre las cuentas del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando selecciona la cuenta "<cuenta>"
    Entonces la cuenta "<cuenta>" muestra saldo 0 y moneda "<moneda>"

    Ejemplos:
      | cuenta | moneda |
      | 1005   | Pound  |
      | 1006   | Rupee  |

  @EXP-031 @alternativo
  Escenario: Ingreso de un cliente sin cuentas
    Dado que el administrador registró los clientes
      | nombre | apellido    | codigo_postal |
      | Sofia  | QASinCuenta | QA0004        |
    Cuando inicia sesión como el cliente "Sofia QASinCuenta"
    Entonces se muestra el mensaje de bienvenida "Welcome Sofia QASinCuenta !!"
    Y se muestra el mensaje "Please open an account with us."
    Y no se muestran las opciones "Transactions", "Deposit" y "Withdrawl"

  @EXP-032 @exitoso
  Escenario: Cerrar sesión del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando cierra sesión
    Entonces la ruta de la aplicación es "#/customer"
    Y el selector de clientes muestra la opción "---Your Name---"
    Y no se muestran los botones "Logout" y "Login"

  @EXP-033 @negativo @defecto @HAL-02
  Escenario: Volver a la cuenta con el botón Atrás después de cerrar sesión
    Dado que el cliente "Harry Potter" inició sesión
    Y cerró sesión
    Cuando regresa con el botón Atrás del navegador
    Entonces no se muestra la información de la cuenta
    Y no puede realizar operaciones sin iniciar sesión de nuevo

  @EXP-034 @alternativo
  Escenario: Uso del botón Home durante la sesión del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando pulsa el botón "Home"
    Entonces la ruta de la aplicación es "#/login"
    Y no se muestra el botón "Logout"
    Cuando abre el inicio de sesión de cliente sin seleccionar un nombre
    Entonces el selector de clientes muestra la opción "---Your Name---"
