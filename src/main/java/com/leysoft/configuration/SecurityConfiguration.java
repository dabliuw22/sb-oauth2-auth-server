
package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.leysoft.util.SecurityUtils;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value(
            value = "${security.matchers.login}")
    private String loginPath;

    @Value(
            value = "${jwt.signature}")
    private String signature;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", loginPath, "/actuator/**", "/clients**")
                .permitAll().antMatchers(HttpMethod.POST, "/user").permitAll().anyRequest()
                .authenticated();
        http.formLogin().loginPage(loginPath).usernameParameter(SecurityUtils.Name.USERNAME_NAME)
                .passwordParameter(SecurityUtils.Name.PASW_NAME).defaultSuccessUrl("/", true)
                .failureUrl("/login?error");
        http.rememberMe().rememberMeParameter("remember-me")
                .tokenValiditySeconds(60 * 60 * 60 * 24 * 5).key("key_remember");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me");
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.afterPropertiesSet();
        return authenticationProvider;
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
