
package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.leysoft.util.GrantTypes;
import com.leysoft.util.Scope;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value(
            value = "${jwt.signature}")
    private String signatureKey;

    @Value(
            value = "${oauth2.client.id}")
    private String clientId;

    @Value(
            value = "${oauth2.client.secret}")
    private String secretClient;

    @Value(
            value = "${oauth2.client.redirect-uri}")
    private String redirectUrl;

    @Value(
            value = "${oauth2.client.access-token-validity-seconds}")
    private int accessTokenValiditySeconds;

    @Value(
            value = "${oauth2.client.refresh-token-validity-seconds}")
    private int refreshTokenValiditySeconds;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(secretClient))
                .redirectUris(redirectUrl)
                .authorizedGrantTypes(GrantTypes.AUTHORIZATION_CODE.toString(),
                        GrantTypes.REFRESH_TOKEN.toString())
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                .scopes(Scope.READ.toString());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signatureKey);
        return converter;
    }
}
