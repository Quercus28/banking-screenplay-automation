# language: es
@administrador @gestion_clientes
Característica: Gestión de clientes
  Como administrador bancario
  Quiero consultar, buscar, ordenar y eliminar clientes
  Para mantener actualizada la información de los clientes del banco

  Antecedentes:
    Dado que el administrador registró los clientes
      | nombre | apellido  | codigo_postal |
      | Ana    | QAPrueba  | QA0001        |
      | Luis   | QAExplora | QA0002        |
    Y abrió una cuenta en "Dollar" para el cliente "Ana QAPrueba"
    Y abrió una cuenta en "Rupee" para el cliente "Luis QAExplora"
    Y está en la pestaña "Customers"

  @EXP-021 @exitoso
  Escenario: Consultar el listado de clientes
    Entonces el listado de clientes tiene las columnas "First Name", "Last Name", "Post Code", "Account Number" y "Delete Customer"
    Y el listado muestra 7 clientes
    Y cada cliente tiene un botón "Delete"

  @EXP-022 @exitoso
  Escenario: Buscar cliente por nombre
    Cuando busca "Harry" en el listado de clientes
    Entonces el listado muestra únicamente al cliente "Harry" "Potter" con código postal "E725JB" y cuentas "1004 1005 1006"

  @EXP-023 @exitoso
  Escenario: Buscar cliente por apellido
    Cuando busca "QAExplora" en el listado de clientes
    Entonces el listado muestra 1 cliente
    Y el listado muestra al cliente "Luis" "QAExplora" con código postal "QA0002"

  @EXP-024 @alternativo
  Escenario: Buscar con coincidencia parcial
    Cuando busca "ott" en el listado de clientes
    Entonces el listado muestra los clientes
      | Harry Potter       |
      | Neville Longbottom |

  @EXP-025 @alternativo
  Esquema del escenario: Buscar por código postal y por número de cuenta
    Cuando busca "<texto>" en el listado de clientes
    Entonces el listado muestra únicamente al cliente "<cliente>"

    Ejemplos:
      | texto  | cliente      |
      | E725JB | Harry Potter |
      | 1004   | Harry Potter |

  @EXP-026 @negativo
  Escenario: Buscar un cliente inexistente
    Cuando busca "ZZZNoExiste" en el listado de clientes
    Entonces el listado muestra 0 clientes

  @EXP-027 @alternativo @HAL-12
  Escenario: Ordenar el listado por columnas
    Cuando ordena el listado por la columna "First Name"
    Entonces el listado queda ordenado por "First Name" de forma descendente
    Cuando ordena el listado por la columna "First Name"
    Entonces el listado queda ordenado por "First Name" de forma ascendente
    Cuando ordena el listado por la columna "Post Code"
    Entonces el listado queda ordenado por "Post Code" de forma descendente

  @EXP-028 @exitoso @HAL-11
  Escenario: Eliminar un cliente de prueba
    Cuando elimina al cliente "Luis QAExplora"
    Entonces el listado muestra 6 clientes
    Y el cliente "Luis QAExplora" no aparece en el selector de clientes de "Open Account"
    Y el cliente "Luis QAExplora" no aparece en el selector de clientes de "Customer Login"
