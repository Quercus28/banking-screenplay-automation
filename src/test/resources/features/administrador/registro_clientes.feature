# language: es
@administrador @registro_clientes
Característica: Registro de clientes
  Como administrador bancario
  Quiero registrar clientes nuevos
  Para que puedan tener cuentas en XYZ Bank

  Antecedentes:
    Dado que el administrador está en la pestaña "Add Customer"

  @EXP-007 @exitoso
  Escenario: Registrar un cliente con datos válidos
    Cuando registra un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Entonces se muestra la alerta "Customer added successfully with customer id :" seguida del identificador asignado
    Y el formulario de registro queda vacío
    Y el cliente "Ana" "QAPrueba" con código postal "QA0001" aparece en el listado sin número de cuenta

  @EXP-008 @alternativo
  Escenario: Registrar un segundo cliente con nombre y apellido distintos
    Dado que registró un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Cuando registra un cliente con nombre "Luis", apellido "QAExplora" y código postal "QA0002"
    Entonces el identificador asignado es el siguiente al del cliente anterior
    Y el formulario de registro queda vacío

  @EXP-009 @negativo
  Escenario: Enviar el formulario Add Customer completamente vacío
    Cuando envía el formulario de registro sin diligenciar ningún campo
    Entonces el campo "First Name" muestra la validación de campo obligatorio
    Y no se muestra ninguna alerta
    Y no se registra ningún cliente nuevo

  @EXP-010 @negativo
  Esquema del escenario: Omitir un campo obligatorio
    Cuando registra un cliente con nombre "<nombre>", apellido "<apellido>" y código postal "<codigo_postal>"
    Entonces el campo "<campo_faltante>" muestra la validación de campo obligatorio
    Y no se registra ningún cliente nuevo

    Ejemplos:
      | nombre | apellido  | codigo_postal | campo_faltante |
      | Carla  | QAOmision |               | Post Code      |
      | Carla  |           | QA0003        | Last Name      |

  @EXP-011 @alternativo @HAL-08
  Escenario: Registrar un cliente con caracteres especiales
    Cuando registra un cliente con nombre "José-María", apellido "O'Brien Ñúñez" y código postal "@#$%&*"
    Entonces se muestra la alerta "Customer added successfully with customer id :" seguida del identificador asignado
    Y el cliente "José-María" "O'Brien Ñúñez" con código postal "@#$%&*" aparece en el listado sin número de cuenta

  @EXP-012 @negativo @defecto @HAL-08
  Escenario: Registrar un cliente con nombre y apellido numéricos
    Cuando registra un cliente con nombre "12345", apellido "67890" y código postal "00000"
    Entonces el sistema rechaza el registro por formato de nombre inválido
    Y no se registra ningún cliente nuevo

  @EXP-013 @negativo @defecto @HAL-06
  Escenario: Enviar campos que solo contienen espacios en blanco
    Cuando registra un cliente con nombre "   ", apellido "   " y código postal "   "
    Entonces el sistema informa que los campos son obligatorios o inválidos
    Y no se muestra la alerta "Please check the details. Customer may be duplicate."
    Y no se registra ningún cliente nuevo

  @EXP-014 @negativo
  Escenario: Registrar un cliente duplicado con los mismos datos
    Dado que registró un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Cuando registra un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Entonces se muestra la alerta "Please check the details. Customer may be duplicate."
    Y la búsqueda "QAPrueba" en el listado de clientes devuelve 1 cliente

  @EXP-015 @alternativo
  Escenario: Homónimo con otro código postal
    Dado que registró un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Cuando registra un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA9999"
    Entonces se muestra la alerta "Customer added successfully with customer id :" seguida del identificador asignado

  @EXP-015 @alternativo @defecto @HAL-07
  Escenario: Duplicado con distinta capitalización
    Dado que registró un cliente con nombre "Ana", apellido "QAPrueba" y código postal "QA0001"
    Cuando registra un cliente con nombre "ana", apellido "qaprueba" y código postal "QA0001"
    Entonces se muestra la alerta "Please check the details. Customer may be duplicate."
    Y la búsqueda "prueba" en el listado de clientes devuelve 1 cliente
