package com.slotegrator.steps.API;

import com.slotegrator.apimodels.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GuestAndUserSteps extends BaseExecutor {

    private RegisterPlayerResponse registerPlayerResponse;
    private RegisterPlayerRequest registerPlayerRequest;
    private AuthorizationRequest authorizationRequest;
    private AuthorizationResponse authorizationResponse;
    private ProfileResponse profileResponse;

    @When("I send guest token request")
    public void iSendGuestTokenRequest() {
        currentResponse = getGuestToken("client_credentials", "guest:default");
        currentResponse.then().log().all();
    }

    @Then("Status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertThat(currentResponse.getStatusCode(), is(statusCode));
    }

    @Then("Server response has guest token")
    public void serverResponseHasGuestToken() {
        GuestTokenResponse guestTokenResponse = currentResponse.then().extract().as(GuestTokenResponse.class);
        assertThat(guestTokenResponse.getAccessToken(), is(not(emptyString())));
        guestToken = guestTokenResponse.getAccessToken();
        System.out.println("Token: " + guestToken);
    }

    @Then("Server response has registration token")
    public void serverResponseHasRegistrationToken() {
        authorizationResponse = currentResponse.then().extract().as(AuthorizationResponse.class);
        assertThat(authorizationResponse.getAccessToken(), is(not(emptyString())));
        tokenAfterLogin = authorizationResponse.getAccessToken();
    }

    @When("I send registration request")
    public void iSendRegistrationRequest() {
        registerPlayerRequest = new RegisterPlayerRequest();
        currentResponse = registerPlayer(registerPlayerRequest, guestToken);
        currentResponse.then().log().all();
    }

    @Then("Server registration response matches the documentation")
    public void serverResponseMatchesTheDocumentation() {
        registerPlayerResponse = currentResponse.then().extract().as(RegisterPlayerResponse.class);
        assertThat(registerPlayerResponse.getId(), is(notNullValue()));
        assertThat(registerPlayerResponse.getUsername(), equalTo(registerPlayerRequest.getUsername()));
        assertThat(registerPlayerResponse.getEmail(), equalTo(registerPlayerRequest.getEmail()));
        userId = registerPlayerResponse.getId();
        username = registerPlayerResponse.getUsername();
        userPassword = registerPlayerRequest.getPasswordChange();
        userEmail = registerPlayerRequest.getEmail();
    }

    @Then("Server profile response matches the documentation")
    public void serverProfileResponseMatchesTheDocumentation() {
        profileResponse = currentResponse.then().extract().as(ProfileResponse.class);
        assertThat(profileResponse.getId(), is(notNullValue()));
        assertThat(profileResponse.getId(), equalTo(userId));
        assertThat(profileResponse.getUsername(), equalTo(username));
        assertThat(profileResponse.getEmail(), equalTo(userEmail));
    }

    @When("I log in as the registered player")
    public void iLogInAsRegisteredPlayer() {
        authorizationRequest = new AuthorizationRequest(username, userPassword);
        currentResponse = authorizationByCreatedPlayer(authorizationRequest);
        currentResponse.then().log().all();
    }

    @When("I send own profile data request")
    public void iSendProfileDataRequest() {
        currentResponse = getUserProfile(userId, tokenAfterLogin);
        currentResponse.then().log().all();
    }

    @When("I send other profile data request")
    public void iSendOtherProfileDataRequest() {
        currentResponse = getUserProfile(userId + 1, tokenAfterLogin);
        currentResponse.then().log().all();
    }
}
