package com.harrison.springboot.steps;

import com.harrison.springboot.api.BaseApi;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

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
}
