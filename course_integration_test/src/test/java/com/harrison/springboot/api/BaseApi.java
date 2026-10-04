package com.harrison.springboot.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    private final Map<String, String> apiGlobalStore;

    @Getter
    private String token;

    public BaseApi() {
        configuration = AppConfiguration.getInstance();
        username = configuration.loginUsername();
        password = configuration.loginPassword();
        token = "";
        apiGlobalStore = new HashMap<>();
    }

    /* 
        General Interaction Methods
    */

    public void verifyStatus() {
        makeGet("/actuator/health");
    }

    public Response tryLogin(String body) {
        return makePost("/login", body);
    }

    public void login() {
        Response response = this.tryLogin(buildBodyCorrectLogin());

        token = response.jsonPath().getString("token");
    }

    /*
        Body Construction Methods
    */

    public String buildBodyCorrectLogin() {
        return String.format(LOGIN_BODY_TEMPLATE, this.username, this.password);
    }

    public String buildBodyWithIncorrectProperties() {
        return String.format(LOGIN_BODY_TEMPLATE, configuration.incorrectLoginUsername(),
                configuration.incorrectLoginPassword());
    }

    /*
        Store Methods
    */

    public Map<String, String> storeValue(String key, String value) {
        apiGlobalStore.put(key, value);
        return apiGlobalStore;
    }

    public Optional<String> getStoredValue(String key) {
        return Optional.ofNullable(apiGlobalStore.get(key));
    }

    public void flushStore() {
        apiGlobalStore.clear();
    }

    /* 
        Get Helpers
    */

    public Response makeGet(String path) {
        return makeGet(path);
    }

    public Response makeGet(String path, Boolean authenticated) {
        return buildRequestSpecification(path, authenticated)
                .when()
                .get();
    }

    /* 
        Post Helpers
    */

    public Response makePost(String path, String body) {
        return makePost(path, body, false);
    }

    public Response makePost(String path, String body, Boolean authenticated) {
        return buildRequestSpecification(path, body, authenticated)
                .when()
                .post();
    }

    /*
        Header construction methods
    */

    private Header buildAuthorizationHeader() {
        return new Header(AUTHORIZATION_HEADER, BEARER_STRING + " " + this.token);
    }

    /*
        Request Specification Builders
    */

    private RequestSpecification buildRequestSpecification(String path) {
        return buildRequestSpecification(path, null, false);
    }

    private RequestSpecification buildRequestSpecification(String path, String body) {
        return buildRequestSpecification(path, body, false);
    }

    private RequestSpecification buildRequestSpecification(String path, Boolean authenticated) {
        return buildRequestSpecification(path, null, authenticated);
    }

    private RequestSpecification buildRequestSpecification(String path, String body, Boolean authenticated) {
        return buildRequestSpecification(path, body, authenticated, false);
    }

    private RequestSpecification buildRequestSpecification(String path, String body, Boolean authenticated,
            Boolean enableLogging) {
        RequestSpecification spec = SerenityRest.given()
                .contentType(configuration.contentType())
                .baseUri(configuration.baseUri())
                .basePath(path);

        Optional.ofNullable(body).ifPresent(spec::body);

        if (authenticated)
            spec.header(buildAuthorizationHeader());

        if (enableLogging)
            spec.log().all();

        return spec;
    }
}
