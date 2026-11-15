# language: es
@administrador @apertura_cuentas
Característica: Apertura de cuentas
  Como administrador bancario
  Quiero abrir cuentas en distintas monedas para los clientes
  Para que puedan realizar operaciones bancarias

  Antecedentes:
    Dado que el administrador registró los clientes
      | nombre | apellido  | codigo_postal |
      | Ana    | QAPrueba  | QA0001        |
      | Luis   | QAExplora | QA0002        |
    Y está en la pestaña "Open Account"

  @EXP-016 @exitoso
  Escenario: Abrir una cuenta en Dollar para un cliente de prueba
    Entonces el selector de moneda ofrece las opciones "Dollar", "Pound" y "Rupee"
    Cuando abre una cuenta en "Dollar" para el cliente "Ana QAPrueba"
    Entonces se muestra la alerta "Account created successfully with account Number :" seguida del número de cuenta asignado
    Y los selectores de cliente y moneda vuelven a su opción vacía

  @EXP-017 @alternativo
  Escenario: Abrir una segunda cuenta en otra moneda para el mismo cliente
    Dado que abrió una cuenta en "Dollar" para el cliente "Ana QAPrueba"
    Cuando abre una cuenta en "Pound" para el cliente "Ana QAPrueba"
    Entonces el número de cuenta asignado es el siguiente al de la cuenta anterior
    Y el cliente "Ana QAPrueba" tiene 2 cuentas en el listado de clientes

  @EXP-018 @alternativo
  Escenario: Abrir una cuenta en Rupee para otro cliente y verificar la asociación
    Dado que abrió una cuenta en "Dollar" para el cliente "Ana QAPrueba"
    Cuando abre una cuenta en "Rupee" para el cliente "Luis QAExplora"
    Entonces se muestra la alerta "Account created successfully with account Number :" seguida del número de cuenta asignado
    Y en el listado de clientes cada cuenta aparece junto a su cliente
      | cliente        | cuentas |
      | Ana QAPrueba   | 1       |
      | Luis QAExplora | 1       |

  @EXP-019 @negativo
  Escenario: Procesar la apertura de cuenta sin seleccionar cliente
    Cuando procesa la apertura de una cuenta en "Dollar" sin seleccionar cliente
    Entonces el campo "Customer" muestra la validación de selección obligatoria
    Y no se muestra ninguna alerta
    Y no se crea ninguna cuenta nueva

  @EXP-020 @negativo
  Escenario: Procesar la apertura de cuenta sin seleccionar moneda
    Cuando procesa la apertura de una cuenta para el cliente "Luis QAExplora" sin seleccionar moneda
    Entonces el campo "Currency" muestra la validación de selección obligatoria
    Y no se muestra ninguna alerta
    Y no se crea ninguna cuenta nueva
