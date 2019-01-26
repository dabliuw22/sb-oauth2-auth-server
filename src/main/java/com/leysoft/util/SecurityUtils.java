
package com.leysoft.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static PasswordEncoder getBCryptPasswordEncoderInstance() {
        return new BCryptPasswordEncoder();
    }

    public static PasswordEncoder getNoopPasswordEncoderInstance() {
        return new PasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
        };
    }
}
