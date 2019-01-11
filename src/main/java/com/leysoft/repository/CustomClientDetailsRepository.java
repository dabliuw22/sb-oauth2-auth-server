
package com.leysoft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leysoft.entity.CustomClientDetails;

public interface CustomClientDetailsRepository extends CrudRepository<CustomClientDetails, String> {

    public CustomClientDetails findByClientId(String clientId);

    @Query(
            value = "UPDATE CustomClientDetails AS c SET c.clientId = ?1, c.clientSecret = ?2")
    public void updateClientIdAndClientSecret(String clientId, String clientSecret);
}
