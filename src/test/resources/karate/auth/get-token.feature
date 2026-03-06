Feature: Obtener token JWT

  Scenario: Login y retornar token
    * def correo = (typeof correo != 'undefined') ? correo : 'alopez22@gmail.com'
    * def contrasena = (typeof contrasena != 'undefined') ? contrasena : '12345678'
    Given url baseUrl + '/auth/login'
    And request { correo: '#(correo)', contrasena: '#(contrasena)' }
    When method POST
    Then status 200
    * def token = response.token