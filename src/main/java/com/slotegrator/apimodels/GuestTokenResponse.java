package com.slotegrator.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestTokenResponse {

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
