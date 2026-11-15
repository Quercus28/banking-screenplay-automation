# language: es
@cliente @transferencias
Característica: Transferencias entre cuentas
  Como cliente bancario
  Quiero transferir dinero entre cuentas
  Para mover mi saldo sin retirarlo y depositarlo

  # La aplicación no ofrece transferencias (HAL-13). El escenario queda como
  # prueba manual para documentar la diferencia entre el enunciado y la aplicación.
  @EXP-056 @negativo @bloqueado @HAL-13 @manual
  Escenario: Verificar la existencia de la funcionalidad de transferencias
    Dado que el cliente "Harry Potter" inició sesión
    Entonces se muestra una opción para transferir entre cuentas
    Cuando ingresa como administrador
    Entonces se muestra una opción relacionada con transferencias
