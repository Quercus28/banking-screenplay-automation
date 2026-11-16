# language: es
@manager @HU-04
Característica: HU-04 - Eliminar clientes
  Como administrador bancario,
  quiero eliminar a un cliente,
  para que deje de figurar en el banco y ya no pueda ingresar a sus cuentas.

  Antecedentes:
    Dado que el administrador registró los clientes
      | nombre | apellido  | codigo_postal |
      | Ana    | QAPrueba  | QA0001        |
      | Luis   | QAExplora | QA0002        |
    Y abrió una cuenta en "Dollar" para el cliente "Ana QAPrueba"
    Y abrió una cuenta en "Rupee" para el cliente "Luis QAExplora"
    Y está en la pestaña "Customers"

  @CA-HU04-01 @CA-HU04-02 @EXP-028 @exitoso @HAL-11
  Escenario: Eliminar un cliente de prueba
    Cuando elimina al cliente "Luis QAExplora"
    Entonces el listado muestra 6 clientes
    Y el cliente "Luis QAExplora" no aparece en el selector de clientes de "Open Account"
    Y el cliente "Luis QAExplora" no aparece en el selector de clientes de "Customer Login"
