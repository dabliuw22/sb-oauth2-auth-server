
package com.leysoft.service.inter;

import java.util.UUID;

import com.leysoft.dto.ClientDto;
import com.leysoft.entity.Client;

public interface ClientService {

    public ClientDto findByClientId(String clientId);

    public boolean save(ClientDto client);

    public default ClientDto toDto(Client entity) {
        ClientDto dto = new ClientDto();
        dto.setClientId(entity.getClientId());
        dto.setRedirectUrl(entity.getRedirectUrl());
        dto.setScopes(entity.getScopes());
        return dto;
    }

    public default Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setClientId(dto.getClientId());
        client.setScopes(dto.getScopes());
        client.setRedirectUrl(dto.getRedirectUrl());
        client.setSecretClient(UUID.randomUUID().toString());
        return client;
    }
}
