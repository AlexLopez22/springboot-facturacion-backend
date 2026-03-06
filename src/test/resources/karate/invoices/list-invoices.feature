Feature: Listar facturas - GET /invoices/list-invoices

  Background:
    * url baseUrl
    * def auth = call read('classpath:karate/auth/get-token.feature') { correo: 'alopez22@gmail.com', contrasena: '12345678' }
    * def token = auth.token
    * header Authorization = 'Bearer ' + token

  @smoke
  Scenario: Listar todas las facturas exitosamente
    Given path '/invoices/list-invoices'
    When method GET
    Then status 200
    And match response == '#[]'

  Scenario: Listar facturas sin token retorna 401 o 403
    Given path '/invoices/list-invoices'
    And header Authorization = ''
    When method GET
    Then match responseStatus != 200

  @smoke
  Scenario: Obtener factura por ID existente
    Given path '/invoices/list-invoices'
    When method GET
    Then status 200
    * def primeraFactura = response[0]
    * def idFactura = primeraFactura.id
    Given path '/invoices/list-invoices/' + idFactura
    And header Authorization = 'Bearer ' + token
    When method GET
    Then status 200
    And match response.id == idFactura

  Scenario: Obtener factura con ID inexistente retorna 404
    Given path '/invoices/list-invoices/999999999'
    When method GET
    Then match responseStatus != 200