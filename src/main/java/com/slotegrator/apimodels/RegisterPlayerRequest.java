package com.slotegrator.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class RegisterPlayerRequest {

    @JsonProperty("username")
    private final String username;
    @JsonProperty("password_change")
    private final String passwordChange;
    @JsonProperty("password_repeat")
    private final String passwordRepeat;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("currency_code")
    private String currencyCode;

    public RegisterPlayerRequest() {
        String timestamp = ZonedDateTime.now(ZoneId.of("EST", ZoneId.SHORT_IDS)).format(DateTimeFormatter.ofPattern("uuuuMMddHHmmss"));
        String newName = "test_" + timestamp;
        String newPassword = Base64.getEncoder().encodeToString(timestamp.getBytes());
        username = newName;
        passwordChange = newPassword;
        passwordRepeat = newPassword;
        email = newName + "@slotegratortest.com";
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordChange() {
        return passwordChange;
    }

    public String getEmail() {
        return email;
    }

}
