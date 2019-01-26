
package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leysoft.util.Role;
import com.leysoft.util.SecurityUtils;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value(
            value = "${spring.security.user.name}")
    private String username;

    @Value(
            value = "${spring.security.user.password}")
    private String password;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(username)
                .password(passwordEncoder().encode(password)).roles(Role.USER.toString());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/actuator/**", "/clients**").permitAll().anyRequest()
                .authenticated().and().httpBasic().and().csrf().disable().headers().frameOptions()
                .disable().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return SecurityUtils.getBCryptPasswordEncoderInstance();
    }

    @Bean(
            name = {
                "noopPasswordEncoder"
            })
    public PasswordEncoder noopPasswordEncoder() {
        return SecurityUtils.getNoopPasswordEncoderInstance();
    }
}
