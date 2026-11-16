# language: es
@customer @HU-06
Característica: HU-06 - Consultar el saldo de cada cuenta
  Como cliente,
  quiero cambiar entre mis cuentas,
  para conocer el saldo y la moneda de cada una antes de hacer una operación.

  # CA-HU06-02 (una operación no afecta las otras cuentas) se verifica en
  # customer/transactions.feature (EXP-050).

  @CA-HU06-01 @EXP-030 @alternativo
  Esquema del escenario: Cambio entre las cuentas del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando selecciona la cuenta "<cuenta>"
    Entonces la cuenta "<cuenta>" muestra saldo 0 y moneda "<moneda>"

    Ejemplos:
      | cuenta | moneda |
      | 1005   | Pound  |
      | 1006   | Rupee  |
