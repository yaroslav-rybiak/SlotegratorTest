package com.slotegrator.steps.API;

import com.slotegrator.apimodels.AuthorizationRequest;
import com.slotegrator.apimodels.GuestTokenRequest;
import com.slotegrator.apimodels.RegisterPlayerRequest;
import com.slotegrator.params.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class BaseExecutor {
    public static String tokenAfterLogin;
    public static Integer userId;
    public static String username;
    public static String userPassword;
    public static String userEmail;
    public static Response currentResponse;
    public static String guestToken;
    private final String authorizationBasicHeader = "Basic " + convertStringToBase64(Constants.AUTHENTICATION_USERNAME);
    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Constants.API_URL)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String convertStringToBase64(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    public Response getGuestToken(String grantType, String scope) {

        return given()
                .spec(requestSpecification)
                .header("Authorization", authorizationBasicHeader)
                .body(new GuestTokenRequest(grantType, scope))
                .post(Constants.CLIENT_CREDENTIALS_GRANT);
    }

    public Response registerPlayer(RegisterPlayerRequest body, String guestToken) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + guestToken)
                .body(body)
                .post(Constants.REGISTRATION);
    }

    public Response authorizationByCreatedPlayer(AuthorizationRequest body) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", authorizationBasicHeader)
                .body(body)
                .post(Constants.AUTHORIZATION);
    }

    public Response getUserProfile(Integer userId, String tokenAfterLogin) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + tokenAfterLogin)
                .get(Constants.GET_PROFILE + userId);
    }
}
