
package com.leysoft.util;

public enum Role {

    USER("USER"), ADMIN("ADMIN");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
