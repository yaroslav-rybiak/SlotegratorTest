package com.slotegrator.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationRequest {

    @JsonProperty("grant_type")
    private final String grantType;

    @JsonProperty("username")
    private final String username;

    @JsonProperty("password")
    private final String password;

    public AuthorizationRequest(String username, String password) {
        this.grantType = "password";
        this.username = username;
        this.password = password;
    }


}
