# language: es
@customer @HU-09
Característica: HU-09 - Retirar dinero
  Como cliente,
  quiero retirar dinero de una de mis cuentas,
  para usar mi saldo cuando lo necesite.

  Antecedentes:
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"

  @CA-HU09-01 @EXP-042 @exitoso
  Escenario: Retiro válido
    Dado que la cuenta tiene saldo 1850
    Cuando retira 300
    Entonces se muestra el mensaje "Transaction successful"
    Y el saldo de la cuenta es 1550

  @CA-HU09-03 @EXP-043 @negativo
  Escenario: Retiro superior al saldo
    Dado que la cuenta tiene saldo 1550
    Cuando retira 2550
    Entonces se muestra el mensaje "Transaction Failed. You can not withdraw amount more than the balance."
    Y el saldo de la cuenta es 1550

  @CA-HU09-02 @CA-HU09-03 @EXP-044 @alternativo
  Escenario: Retiro del saldo total exacto y nuevo retiro con saldo cero
    Dado que la cuenta tiene saldo 1550
    Cuando retira 1550
    Entonces se muestra el mensaje "Transaction successful"
    Y el saldo de la cuenta es 0
    Cuando retira 1
    Entonces se muestra el mensaje "Transaction Failed. You can not withdraw amount more than the balance."
    Y el saldo de la cuenta es 0

  @CA-HU09-05 @EXP-045 @negativo @defecto @HAL-05
  Escenario: Retiro de cero
    Dado que la cuenta tiene saldo 1550
    Cuando retira 0
    Entonces se muestra un mensaje de error de monto inválido
    Y el saldo de la cuenta es 1550

  @CA-HU09-06 @EXP-046 @negativo @defecto @HAL-05
  Escenario: Retiro negativo
    Dado que la cuenta tiene saldo 1550
    Cuando retira -50
    Entonces se muestra un mensaje de error de monto inválido
    Y el saldo de la cuenta es 1550

  @CA-HU09-04 @EXP-047 @negativo
  Escenario: Retiro con el campo vacío
    Dado que la cuenta tiene saldo 1550
    Cuando envía el retiro sin escribir un monto
    Entonces el campo de monto muestra la validación de campo obligatorio
    Y el saldo de la cuenta es 1550

  @EXP-048 @negativo @HAL-15
  Escenario: Retiro con valor decimal
    Dado que la cuenta tiene saldo 1550
    Cuando retira 5.5
    Entonces el campo de monto muestra la validación de valor no válido
    Y el saldo de la cuenta es 1550

  @EXP-048 @negativo @HAL-15
  Escenario: Retiro con caracteres no numéricos
    Dado que la cuenta tiene saldo 1550
    Cuando intenta escribir "xyz" como monto del retiro y lo envía
    Entonces el campo de monto queda vacío
    Y el campo de monto muestra la validación de campo obligatorio
    Y el saldo de la cuenta es 1550
