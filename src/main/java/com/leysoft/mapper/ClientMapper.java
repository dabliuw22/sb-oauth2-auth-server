
package com.leysoft.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.leysoft.entity.Client;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setClientId(rs.getString(1));
        client.setSecretClient(rs.getString(2));
        client.setScopes(Arrays.asList(rs.getString(3).split(",")));
        client.setRedirectUrl(rs.getString(4));
        return client;
    }
}
