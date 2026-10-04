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

  @ProductTest @ProductFullProcessTest
  Scenario: Verificar que se pueda crear un producto
    When realizo una solicitud de crear un producto con los siguientes datos:
      |  sku          | name        | description          | price |
      |  ioioioill    | Producto 1  | Descripcion producto | 501   |
    Then la respuesta debe contener un id valido para guardarlo con la clave "productId"
    Then verifico que el producto creado con el id guardado "productId" tenga el nombre "Producto 1" y el precio 501
