Feature: Verificación de status

    Como consumidor de la API
    Quiero realizar una petición GET al servicio
    Para validar que este se encuentre disponible

    @BasicTest
    Scenario: Verificar status del servicio
        When verifico el status del servicio
        Then el servicio debe responder con status 200

    @LoginTest
    Scenario: Verificar que el sistema no deja iniciar sesión con credenciales incorrectas
        When intento realizar login con nombre de usuario 'adminsss' y contrasena 'kkkkkk'
        Then verifico que el login ha sido fallido