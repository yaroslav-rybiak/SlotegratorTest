package com.slotegrator.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("country_id")
    private String countryId;
    @JsonProperty("timezone_id")
    private String timezoneId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("birthdate")
    private String birthdate;
    @JsonProperty("bonuses_allowed")
    private boolean bonusesAllowed;
    @JsonProperty("is_verified")
    private boolean isVerified;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


}
