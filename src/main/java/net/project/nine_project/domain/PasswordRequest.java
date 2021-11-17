package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordRequest {
    @JsonProperty("password_old")
    private String passwordOld;
    @JsonProperty("password_new")
    private String passwordNew;

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    @Override
    public String toString() {
        return "PasswordRequest{" +
                "passwordOld='" + passwordOld + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}
