Feature: Crear facturas - POST /invoices/create-invoices

Background:
    * url baseUrl

@smoke
Scenario: Crear factura válida exitosamente
    * def auth = call read('classpath:karate/auth/get-token.feature') { correo: 'alopez22@gmail.com', contrasena: '12345678' }
    * def token = auth.token
    * header Authorization = 'Bearer ' + token

    * def invoiceBase =
  """
  {
    "tipoDocumento": 1,
    "serie": 24,
    "numero": "00000010",
    "moneda": "PEN",
    "tipoOperacion": "0101",
    "clienteId": 18,
    "emisorId": 1,
    "formaPagoId": 2,
    "fechaEmision": "2026-01-01",
    "horaEmision": "20:00:00",
    "items": [
      {
        "item": 1,
        "productoId": 1,
        "codigoProducto": "P0rt04",
        "descripcion": "Laptop Lenovo ThinkPad X1 Carbon",
        "cantidad": 1,
        "unidadMedida": "NIU",
        "valorUnitario": 3813.56,
        "precioUnitario": 4500.00,
        "valorVenta": 3813.56,
        "afectacionIgv": "10",
        "importeTotal": 4500.00
      }
    ],
    "totales": {
      "opGravada": 3813.56,
      "opExonerada": 0.00,
      "opInafecta": 0.00,
      "opGratuita": 0.00,
      "igv": 686.44,
      "totalImpuestos": 686.44,
      "importeTotal": 4500.00
    },
    "cuotas": [
      {
        "numeroCuota": 1,
        "fechaVencimiento": "2026-02-01",
        "importe": 2250.00
      },
      {
        "numeroCuota": 2,
        "fechaVencimiento": "2026-03-01",
        "importe": 2250.00
      }
    ]
  }
  """

    Given path '/invoices/create-invoices'
    And request invoiceBase
    When method POST
    Then status 200

@negative
Scenario: Acceso sin token retorna 401 o 403
    Given path '/invoices/list-invoices'
    And header Authorization = ''
    When method GET
    Then match [401,403] contains responseStatus
