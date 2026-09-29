Feature: Verificación del endpoint de productos
    Como consumidor de la API
    Quiero verificar el funcionamineto del API de productos
    Para validar que los endpoints relacionados funcionan correctamente

  Background:
    Given que se ha iniciado sesion en la aplicacion
    And se valida el token

  @ProductTest
  Scenario: Verificar que se listen los productos
    When realizo una solicitud de listar todos los productos
    Then la respuesta debe ser una lista no vacia
