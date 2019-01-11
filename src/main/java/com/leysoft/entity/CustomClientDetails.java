
package com.leysoft.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

@Entity
@Table(
        name = "oauth_client_details")
public class CustomClientDetails implements ClientDetails {

    private static final long serialVersionUID = 1322479421817845697L;

    @Id
    @Column(
            name = "client_id",
            nullable = false)
    private String clientId;

    @Column(
            name = "client_secret",
            nullable = false)
    private String clientSecret;

    @Column(
            name = "resource_ids",
            nullable = true)
    private String resourceIds;

    @Column(
            name = "scope",
            nullable = false)
    private String scope;

    @Column(
            name = "authorized_grant_types",
            nullable = false)
    private String authorizedGrantTypes;

    @Column(
            name = "web_server_redirect_uri",
            nullable = false)
    private String registeredRedirectUris;

    @Column(
            name = "autoapprove")
    private String autoApproveScopes;

    @Column(
            name = "authorities",
            nullable = true)
    private String authorities;

    @Column(
            name = "access_token_validity",
            nullable = false)
    private Integer accessTokenValiditySeconds;

    @Column(
            name = "refresh_token_validity",
            nullable = false)
    private Integer refreshTokenValiditySeconds;

    @Column(
            name = "additional_information",
            nullable = true)
    private String additionalInformation;

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return StringUtils.commaDelimitedListToSet(resourceIds);
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void addResourceIds(String resourceId) {
        this.resourceIds =
                (this.resourceIds == null) ? resourceId : this.resourceIds.concat("," + resourceId);
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void addScope(String scope) {
        this.scope = (this.scope == null) ? scope : this.scope.concat("," + scope);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return StringUtils.commaDelimitedListToSet(authorizedGrantTypes);
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void addAuthorizedGrantType(String authorizedGrantType) {
        this.authorizedGrantTypes = (this.authorizedGrantTypes == null) ? authorizedGrantType
                : this.authorizedGrantTypes.concat("," + authorizedGrantType);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(registeredRedirectUris);
    }

    public void setRegisteredRedirectUris(String registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    public void addRegisteredRedirectUr(String registeredRedirectUri) {
        this.registeredRedirectUris = (this.registeredRedirectUris == null) ? registeredRedirectUri
                : this.registeredRedirectUris.concat("," + registeredRedirectUri);
    }

    public Set<String> getAutoApproveScopes() {
        return StringUtils.commaDelimitedListToSet(autoApproveScopes);
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> result = new HashSet<>();
        if (this.authorities != null) {
            Set<String> set = StringUtils.commaDelimitedListToSet(this.authorities);
            set.forEach(SimpleGrantedAuthority::new);
        }
        return result;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        this.authorities =
                (this.authorities == null) ? authority : this.authorities.concat("," + authority);
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        Set<String> autoApproves = getAutoApproveScopes();
        if (autoApproves == null) {
            return false;
        }
        for (String auto : autoApproves) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> additionalInformations = new HashMap<>();
        if (this.additionalInformation != null) {
            String[] informations = this.additionalInformation.split(",");
            for (String information : informations) {
                String[] pair = information.split("=");
                if (pair.length == 2) {
                    additionalInformations.put(pair[0], pair[1]);
                }
            }
        }
        return additionalInformations;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public void addAdditionalInformation(String key, Object value) {
        this.additionalInformation =
                (this.additionalInformation == null) ? key + "=" + value.toString()
                        : this.additionalInformation.concat("," + additionalInformation);
    }
}
