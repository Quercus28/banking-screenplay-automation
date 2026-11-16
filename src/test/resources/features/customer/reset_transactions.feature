# language: es
@customer @HU-12
Característica: HU-12 - Reiniciar el historial de una cuenta
  Como cliente,
  quiero reiniciar el historial de una de mis cuentas,
  para dejarla sin movimientos y empezar un registro nuevo.

  @CA-HU12-01 @EXP-055 @alternativo @HAL-10
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
