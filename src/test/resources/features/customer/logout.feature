# language: es
@customer @HU-07
Característica: HU-07 - Cerrar sesión
  Como cliente,
  quiero cerrar mi sesión,
  para que nadie más pueda ver mi información ni hacer operaciones con mis cuentas desde el mismo equipo.

  @CA-HU07-01 @EXP-032 @exitoso
  Escenario: Cerrar sesión del cliente
    Dado que el cliente "Harry Potter" inició sesión
    Cuando cierra sesión
    Entonces la ruta de la aplicación es "#/customer"
    Y el selector de clientes muestra la opción "---Your Name---"
    Y no se muestran los botones "Logout" y "Login"

  @CA-HU07-02 @EXP-033 @negativo @defecto @HAL-02
  Escenario: Volver a la cuenta con el botón Atrás después de cerrar sesión
    Dado que el cliente "Harry Potter" inició sesión
    Y cerró sesión
    Cuando regresa con el botón Atrás del navegador
    Entonces no se muestra la información de la cuenta
    Y no puede realizar operaciones sin iniciar sesión de nuevo

  @CA-HU07-03 @EXP-005 @negativo @defecto @HAL-01
  Escenario: Acceso directo sin sesión a la ruta de la cuenta del cliente
    Dado que el visitante tiene una sesión nueva del navegador
    Cuando abre directamente la ruta "#/account"
    Entonces es redirigido a la pantalla principal o recibe un mensaje de acceso no autorizado
    Y no se muestran plantillas sin procesar como "{{user}}"
