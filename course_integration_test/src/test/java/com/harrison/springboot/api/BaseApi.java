package com.harrison.springboot.api;

import com.harrison.springboot.configuration.AppConfiguration;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import net.serenitybdd.rest.SerenityRest;

public class BaseApi {
    public static final String LOGIN_BODY_TEMPLATE = """
                {
                    "username": "%s",
                    "password": "%s"
                }
            """;

    private static final String BEARER_STRING = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final AppConfiguration configuration;
    private final String username;
    private final String password;

    @Getter
    private String token;

    public BaseApi() {
        configuration = AppConfiguration.getInstance();
        username = configuration.loginUsername();
        password = configuration.loginPassword();
        token = "";
    }

    public void verifyStatus() {
        SerenityRest.given()
                .baseUri(configuration.baseUri())
                .when()
                .get("/actuator/health");
    }

    public Response tryLogin(String body) {
        return SerenityRest.given()
                .baseUri(configuration.baseUri())
                .contentType(configuration.contentType())
                .body(body)
                .log().all()
                .when()
                .post("/login");
    }

    public Response makeGet(String path) {
        return makeGet(path, false);
    }

    public Response makeGet(String path, Boolean authenticated) {
        RequestSpecification spec = SerenityRest.given()
            .contentType(configuration.contentType())
            .baseUri(configuration.baseUri());

        if (authenticated)
            spec.header(new Header(AUTHORIZATION_HEADER, BEARER_STRING + " " + this.token));

        return spec.when()
            .get(path);
    }

    public void login() {
        Response response = this.tryLogin(String.format(LOGIN_BODY_TEMPLATE, this.username, this.password));

        System.out.println(response.statusCode());

        token = response.jsonPath().getString("token");
    }
}
