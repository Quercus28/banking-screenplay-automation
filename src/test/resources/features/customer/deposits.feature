# language: es
@customer @HU-08
Característica: HU-08 - Depositar dinero
  Como cliente,
  quiero depositar dinero en una de mis cuentas,
  para aumentar el saldo que tengo disponible.

  Antecedentes:
    Dado que el cliente "Ana QAPrueba" tiene una cuenta en "Dollar"

  @CA-HU08-01 @CA-HU02-02 @EXP-035 @exitoso
  Escenario: Depósito válido
    Dado que la cuenta tiene saldo 0
    Cuando deposita 1000
    Entonces se muestra el mensaje "Deposit Successful"
    Y el saldo de la cuenta es 1000
    Y el campo de monto queda vacío

  @CA-HU08-02 @EXP-036 @alternativo
  Escenario: Depósitos consecutivos
    Dado que la cuenta tiene saldo 1000
    Cuando deposita 500
    Entonces se muestra el mensaje "Deposit Successful"
    Y el saldo de la cuenta es 1500
    Cuando deposita 250
    Entonces se muestra el mensaje "Deposit Successful"
    Y el saldo de la cuenta es 1750

  @CA-HU08-04 @EXP-037 @negativo @defecto @HAL-03 @HAL-04
  Escenario: Depósito de cero justo después de un depósito exitoso
    Dado que la cuenta tiene saldo 1500
    Y acaba de depositar 250 con éxito
    Cuando deposita 0
    Entonces se muestra un mensaje de error de monto inválido
    Y no se muestra el mensaje "Deposit Successful"
    Y el saldo de la cuenta es 1750

  @CA-HU08-04 @EXP-037 @negativo @defecto @HAL-03
  Escenario: Depósito de cero con el formulario recién abierto
    Dado que la cuenta tiene saldo 1750
    Cuando deposita 0
    Entonces se muestra un mensaje de error de monto inválido
    Y el saldo de la cuenta es 1750

  @CA-HU08-05 @EXP-038 @negativo @defecto @HAL-03
  Escenario: Depósito negativo
    Dado que la cuenta tiene saldo 1750
    Cuando deposita -100
    Entonces se muestra un mensaje de error de monto inválido
    Y el saldo de la cuenta es 1750

  @CA-HU08-03 @EXP-039 @negativo
  Escenario: Depósito con el campo vacío
    Dado que la cuenta tiene saldo 1750
    Cuando envía el depósito sin escribir un monto
    Entonces el campo de monto muestra la validación de campo obligatorio
    Y el saldo de la cuenta es 1750

  @EXP-040 @alternativo @HAL-15
  Escenario: Depósito con valor decimal
    Dado que la cuenta tiene saldo 1750
    Cuando deposita 10.5
    Entonces el campo de monto muestra la validación de valor no válido
    Y el saldo de la cuenta es 1750

  @EXP-041 @negativo @HAL-15
  Escenario: Depósito con caracteres no numéricos
    Dado que la cuenta tiene saldo 1750
    Cuando intenta escribir "abc" como monto del depósito y lo envía
    Entonces el campo de monto queda vacío
    Y el campo de monto muestra la validación de campo obligatorio
    Y el saldo de la cuenta es 1750

  @EXP-041 @alternativo @HAL-15
  Escenario: Depósito con notación exponencial
    Dado que la cuenta tiene saldo 1750
    Cuando intenta escribir "1e2" como monto del depósito y lo envía
    Entonces se muestra el mensaje "Deposit Successful"
    Y el saldo de la cuenta es 1850
