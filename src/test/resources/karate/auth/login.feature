Feature: Autenticación - POST /auth/login

  Background:
    * url baseUrl

  @smoke
  Scenario: Login exitoso con credenciales válidas
    Given path '/auth/login'
    And request { correo: 'alopez22@gmail.com', contrasena: '12345678' }
    When method POST
    Then status 200
    And match response.token == '#string'

  Scenario: Login falla con contraseña incorrecta
    Given path '/auth/login'
    And request { correo: 'alopez22@gmail.com', contrasena: 'incorrecta' }
    When method POST
    Then status 401

  Scenario: Login falla con correo inexistente
    Given path '/auth/login'
    And request { correo: 'noexiste@test.com', contrasena: '12345678' }
    When method POST
    Then status 401

  Scenario: Login falla sin body
    Given path '/auth/login'
    And request {}
    When method POST
    Then status 401