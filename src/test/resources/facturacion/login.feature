Feature: Obtener token de autenticación

Scenario: Login correcto

Given url 'http://localhost:8080/auth/login'
And header Content-Type = 'application/json'

And request
"""
{
  "correo": "alopez22@gmail.com",
  "contrasena": "12345678"
}
"""

When method post
Then status 200

* def token = response.token
* print 'TOKEN:', token