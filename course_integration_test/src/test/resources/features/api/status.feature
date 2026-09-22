Feature: Verificación de status

    Como consumidor de la API
    Quiero realizar una petición GET al servicio
    Para validar que este se encuentre disponible

    @BasicTest
    Scenario: Verificar status del servicio
        When verifico el status del servicio
        Then el servicio debe responder con status 200