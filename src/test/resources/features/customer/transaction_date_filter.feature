# language: es
@customer @HU-11
Característica: HU-11 - Filtrar movimientos por fecha
  Como cliente,
  quiero filtrar mis movimientos por un rango de fecha y hora,
  para revisar solo las operaciones de un período específico.

  # CA-HU11-01 (filtrar un período con movimientos precargados) se verifica en
  # customer/transactions.feature (EXP-054).

  @CA-HU11-02 @EXP-052 @negativo
  Escenario: Filtro de fechas sin resultados
    Dado que el cliente "Hermoine Granger" inició sesión
    Y selecciona la cuenta "1001"
    Cuando consulta los movimientos de la cuenta
    Y filtra los movimientos desde "2015-01-01T01:00" hasta "2015-01-01T23:00"
    Entonces el historial no muestra movimientos

  @CA-HU11-03 @EXP-051 @alternativo
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

  @CA-HU11-04 @EXP-051 @negativo @defecto @HAL-09
  Escenario: Filtrar movimientos con una fecha final en el futuro
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"
    Y realizó las operaciones
      | tipo     | monto |
      | deposito | 1000  |
    Cuando consulta los movimientos de la cuenta
    Y cambia la fecha final del filtro a 30 minutos en el futuro
    Entonces se informa que el rango de fechas no es permitido
