package com.slotegrator.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestTokenRequest {

    @JsonProperty("grant_type")
    private final String grantType;

    @JsonProperty("scope")
    private final String scope;

    public GuestTokenRequest(String grantType, String scope) {
        this.grantType = grantType;
        this.scope = scope;
    }
}
