
package com.leysoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import com.leysoft.entity.CustomClientDetails;
import com.leysoft.repository.CustomClientDetailsRepository;

@Service
public class JpaClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaClientDetailsService.class);

    @Autowired
    private CustomClientDetailsRepository clientDetailsRepository;

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (clientDetails instanceof CustomClientDetails) {
            String clientSecret = UUID.randomUUID().toString();
            LOGGER.info("SECRET: {}", clientSecret);
            CustomClientDetails client = (CustomClientDetails) clientDetails;
            client.setClientSecret(clientSecret);
            clientDetailsRepository.save(client);
        }
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        if (clientDetails instanceof CustomClientDetails) {
            clientDetailsRepository.save((CustomClientDetails) clientDetails);
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        clientDetailsRepository.updateClientIdAndClientSecret(clientId, secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        clientDetailsRepository.deleteById(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<CustomClientDetails> result =
                (List<CustomClientDetails>) clientDetailsRepository.findAll();
        return new ArrayList<>(result);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepository.findByClientId(clientId);
    }
}
