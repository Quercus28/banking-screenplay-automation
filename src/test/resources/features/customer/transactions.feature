# language: es
@customer @HU-10
Característica: HU-10 - Consultar movimientos
  Como cliente,
  quiero ver el historial de movimientos de mi cuenta,
  para revisar los depósitos y retiros que he hecho.

  @CA-HU10-01 @CA-HU10-02 @EXP-049 @exitoso
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

  @CA-HU10-03 @CA-HU06-02 @CA-HU02-02 @EXP-050 @alternativo
  Escenario: Consultar movimientos de una cuenta sin transacciones
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Pound"
    Cuando consulta los movimientos de la cuenta
    Entonces el historial no muestra movimientos
    Y no se muestran los filtros de fecha ni el botón "Reset"
    Y se muestra el botón "Back"

  @CA-HU10-04 @EXP-053 @alternativo @HAL-12
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

  @CA-HU10-05 @CA-HU11-01 @EXP-054 @alternativo
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
