
package com.leysoft.entity;

import java.util.List;

public class Client {

    private String clientId;

    private String secretClient;

    private List<String> scopes;

    private String redirectUrl;

    public Client() {
    }

    public Client(String clientId, String secretClient, List<String> scopes, String redirectUrl) {
        this.clientId = clientId;
        this.secretClient = secretClient;
        this.scopes = scopes;
        this.redirectUrl = redirectUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecretClient() {
        return secretClient;
    }

    public void setSecretClient(String secretClient) {
        this.secretClient = secretClient;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "Client [clientId=" + clientId + ", scopes=" + scopes + ", redirectUrl="
                + redirectUrl + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        return true;
    }
}
