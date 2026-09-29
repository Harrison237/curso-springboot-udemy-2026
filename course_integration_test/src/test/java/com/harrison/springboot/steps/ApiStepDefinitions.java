package com.harrison.springboot.steps;

import com.harrison.springboot.api.BaseApi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.array;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

public class ApiStepDefinitions {
    private final BaseApi baseApi = new BaseApi();

    @Given("que se ha iniciado sesion en la aplicacion")
    public void login() {
        baseApi.login();
    }

    @And("se valida el token")
    public void validateToken() {
        assertNotNull(baseApi.getToken());
        assertFalse(baseApi.getToken().isBlank());
    }

    @When("verifico el status del servicio")
    public void verifyServiceStatus() {
        baseApi.verifyStatus();
    }

    @Then("el servicio debe responder con status {int}")
    public void verifyServiceStatusCode(Integer statusCode) {
        SerenityRest
                .lastResponse()
                .then()
                .statusCode(statusCode);
    }

    @When("intento realizar login con nombre de usuario {string} y contrasena {string}")
    public void tryLogin(String username, String password) {
        String formatted = String.format(BaseApi.LOGIN_BODY_TEMPLATE, username, password);
        System.out.println(formatted);

        baseApi.tryLogin(formatted);
    }

    @Then("verifico que el login ha sido fallido")
    public void verifyFailedLogin() {
        SerenityRest.lastResponse()
                .then()
                .statusCode(401)
                .body("error", equalTo("Bad credentials"));
    }

    @When("realizo una solicitud de listar todos los productos")
    public void listProducts() {
        baseApi.makeGet("/api/products", true);
    }

    @Then("la respuesta debe ser una lista no vacia")
    public void verifyResponseList() {
        SerenityRest.lastResponse()
            .then()
            .body(is(not(emptyArray())));
    }


}
