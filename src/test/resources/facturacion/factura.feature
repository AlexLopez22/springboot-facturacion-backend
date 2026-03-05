Feature: Crear factura

Scenario: Crear factura correctamente

# Obtener token
* def login = call read('classpath:facturacion/login.feature')
* def token = login.token

Given url 'http://localhost:8080/invoices/create-invoices'

And header Authorization = 'Bearer ' + token
And header Content-Type = 'application/json'

And request
"""
{
  "tipoComprobante": 1,
  "serie": 24,
  "numero": "00000008",
  "moneda": "PEN",
  "tipoOperacion": "0101",
  "fechaEmision": "2026-01-01",
  "horaEmision": "20:00:00",
  "clienteId": 1,
  "emisorId": 1,
  "formaPagoId": 2,
  "totales": {
    "opGravada": 3813.56,
    "opExonerada": 0.00,
    "opInafecta": 0.00,
    "opGratuita": 0.00,
    "igv": 686.44,
    "totalImpuestos": 686.44,
    "importeTotal": 4500.00
  },
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

When method post
Then status 200

And print response