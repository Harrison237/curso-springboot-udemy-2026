package com.harrison.springboot.steps;

import com.harrison.springboot.api.BaseApi;
import com.harrison.springboot.configuration.constants.ProductConstants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.blankOrNullString;
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

    @When("intento realizar login con nombre de usuario y contrasena incorrectos")
    public void tryLoginWithIncorrectProperties() {
        baseApi.tryLogin(baseApi.buildBodyWithIncorrectProperties());
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

    @When("realizo una solicitud de crear un producto con los siguientes datos:")
    public void createProduct(DataTable dataTable) {
        String sku = dataTable.cell(1, 0);
        String name = dataTable.cell(1, 1);
        String desc = dataTable.cell(1, 2);
        String price = dataTable.cell(1, 3);

        String body = ProductConstants.makeProductBodyWithoutId(sku, name, desc, price);
        baseApi.makePost("/api/products", body, true);
    }

    @Then("la respuesta debe contener un id valido para guardarlo con la clave {string}")
    public void verifyResponseContainsIdAndStore(String key) {
        Integer id = SerenityRest.lastResponse()
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        assertNotNull(id);
        assertTrue(id > 0);

        baseApi.storeValue(key, id.toString());
    }

    @Then("verifico que el producto creado con el id guardado {string} tenga el nombre {string} y el precio {int}")
    public void verifyCreatedProduct(String idKey, String name, Integer price) {
        String id = baseApi.getStoredValue(idKey)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró la entrada '" + idKey + "' en el store global"));

        baseApi.makeGet("/api/products/" + id, true)
                .then()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("price", equalTo(price));
    }
}
