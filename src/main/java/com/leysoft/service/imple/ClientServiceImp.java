
package com.leysoft.service.imple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.dto.ClientDto;
import com.leysoft.entity.Client;
import com.leysoft.repository.inter.ClientRepository;
import com.leysoft.service.inter.ClientService;

@Service
public class ClientServiceImp implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDto findByClientId(String clientId) {
        return toDto(clientRepository.findByClientId(clientId));
    }

    @Override
    public boolean save(ClientDto client) {
        Client entity = toEntity(client);
        LOGGER.info("client: {}, secret: {}", entity, entity.getSecretClient());
        return clientRepository.save(entity);
    }
}
