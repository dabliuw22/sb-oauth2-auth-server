
package com.leysoft.repository.imple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.leysoft.constant.Query;
import com.leysoft.entity.Client;
import com.leysoft.exception.NotFoundClientException;
import com.leysoft.mapper.ClientMapper;
import com.leysoft.repository.inter.ClientRepository;

@Repository
public class ClientRepositoryImp implements ClientRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryImp.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Client findByClientId(String clientId) {
        try {
            return jdbcTemplate.queryForObject(Query.GET_CLIENT_BY_CLIENT_ID, new ClientMapper(),
                    clientId);
        } catch (Exception e) {
            LOGGER.error("Not found client-id: {}", clientId);
            throw new NotFoundClientException(e);
        }
    }

    @Override
    public boolean save(Client client) {
        boolean saved = false;
        try {
            jdbcTemplate.update(Query.INSERT_CLIENT, client.getClientId(),
                    encoder.encode(client.getSecretClient()), String.join(",", client.getScopes()),
                    client.getRedirectUrl());
            saved = true;
        } catch (Exception e) {
            LOGGER.error("Error saved cleint: {}", client);
        }
        return saved;
    }
}
