
package com.leysoft.util;

public enum GrantTypes {

    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD_CREDENTIALS("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    private String value;

    private GrantTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
