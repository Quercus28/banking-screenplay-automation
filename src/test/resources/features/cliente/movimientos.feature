# language: es
@cliente @movimientos
Característica: Movimientos de la cuenta
  Como cliente bancario
  Quiero consultar el historial de movimientos de mis cuentas
  Para hacer seguimiento a mis depósitos y retiros

  @EXP-049 @exitoso
  Escenario: Consultar el historial de movimientos
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 1000  |
      | deposito | 500   |
      | retiro   | 300   |
      | retiro   | 5000  |
    Cuando consulta los movimientos de la cuenta
    Entonces la ruta de la aplicación es "#/listTx"
    Y el historial muestra los movimientos
      | monto | tipo   |
      | 1000  | Credit |
      | 500   | Credit |
      | 300   | Debit  |
    Y se muestran los filtros de fecha y los botones "Back" y "Reset"

  @EXP-050 @alternativo
  Escenario: Consultar movimientos de una cuenta sin transacciones
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Pound"
    Cuando consulta los movimientos de la cuenta
    Entonces el historial no muestra movimientos
    Y no se muestran los filtros de fecha ni el botón "Reset"
    Y se muestra el botón "Back"

  @EXP-051 @alternativo
  Escenario: Filtrar movimientos por rango de fechas
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 1000  |
      | deposito | 500   |
    Y después de un minuto realizó un retiro de 200
    Cuando consulta los movimientos de la cuenta
    Entonces el historial muestra 3 movimientos
    Cuando filtra los movimientos desde el minuto del retiro hasta el momento actual
    Entonces el historial muestra los movimientos
      | monto | tipo  |
      | 200   | Debit |

  @EXP-051 @negativo @defecto @HAL-09
  Escenario: Filtrar movimientos con una fecha final en el futuro
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 1000  |
    Cuando consulta los movimientos de la cuenta
    Y cambia la fecha final del filtro a 30 minutos en el futuro
    Entonces se informa que el rango de fechas no es permitido

  @EXP-052 @negativo
  Escenario: Filtro de fechas sin resultados
    Dado que el cliente "Hermoine Granger" inició sesión
    Y selecciona la cuenta "1001"
    Cuando consulta los movimientos de la cuenta
    Y filtra los movimientos desde "2015-01-01T01:00" hasta "2015-01-01T23:00"
    Entonces el historial no muestra movimientos

  @EXP-053 @alternativo @HAL-12
  Escenario: Ordenar movimientos por Date-Time
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 1000  |
      | deposito | 500   |
      | retiro   | 300   |
    Cuando consulta los movimientos de la cuenta
    Y ordena los movimientos por "Date-Time"
    Entonces los movimientos quedan ordenados por fecha de forma descendente
    Cuando ordena los movimientos por "Date-Time"
    Entonces los movimientos quedan ordenados por fecha de forma ascendente

  @EXP-054 @alternativo
  Escenario: Navegación con Back y controles de desplazamiento
    Dado que el cliente "Hermoine Granger" inició sesión
    Y selecciona la cuenta "1001"
    Cuando consulta los movimientos de la cuenta
    Entonces se muestra el control ">" y no se muestran los controles "<" ni "Top"
    Cuando pulsa el control ">"
    Entonces se muestran los controles "<" y "Top"
    Cuando pulsa el control "Top"
    Entonces la tabla vuelve al inicio del historial
    Cuando filtra los movimientos desde "2015-01-01T00:00" hasta "2015-01-03T23:59"
    Entonces el historial muestra 6 movimientos
    Cuando pulsa el botón "Back"
    Entonces la ruta de la aplicación es "#/account"
    Y la cuenta seleccionada es "1001"

  @EXP-055 @alternativo @HAL-10
  Escenario: Reiniciar movimientos con Reset
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 700   |
    Cuando consulta los movimientos de la cuenta
    Y pulsa el botón "Reset"
    Entonces el historial no muestra movimientos
    Cuando pulsa el botón "Back"
    Entonces el saldo de la cuenta es 0
