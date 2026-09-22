package com.harrison.springboot.steps;

import com.harrison.springboot.api.BaseApi;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefinitions {
    private final BaseApi baseApi = new BaseApi();

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
        String body = """
                {
                    "username": %s,
                    "password": %s
                }
                """;
        String formatted = String.format(body, username, password);
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
}
