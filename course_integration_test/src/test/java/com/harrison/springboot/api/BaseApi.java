package com.harrison.springboot.api;

import com.harrison.springboot.configuration.AppConfiguration;
import net.serenitybdd.rest.SerenityRest;

public class BaseApi {
    private final AppConfiguration configuration;

    public BaseApi() {
        configuration = AppConfiguration.getInstance();
    }

    public void verifyStatus() {
        SerenityRest.given()
                .baseUri(configuration.baseUri())
                .when()
                .get("/actuator/health");
    }

    public void tryLogin(String body) {
        SerenityRest.given()
            .baseUri(configuration.baseUri())
            .contentType(configuration.contentType())
            .body(body)
            .when()
            .post("/login");
    }
}
