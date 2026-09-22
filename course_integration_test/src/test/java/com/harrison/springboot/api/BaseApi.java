package com.harrison.springboot.api;

import net.serenitybdd.rest.SerenityRest;
import static com.harrison.springboot.configuration.GlobalConfig.BASE_URI;;

public class BaseApi {
    public void verifyStatus() {
        SerenityRest.given()
            .baseUri(BASE_URI)
            .when().get("/actuator/health");
    }
}
