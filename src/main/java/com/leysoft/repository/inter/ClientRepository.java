
package com.leysoft.repository.inter;

import com.leysoft.entity.Client;

public interface ClientRepository {

    public Client findByClientId(String clientId);

    public boolean save(Client client);
}
