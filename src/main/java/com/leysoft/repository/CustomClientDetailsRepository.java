
package com.leysoft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.leysoft.entity.CustomClientDetails;

public interface CustomClientDetailsRepository extends CrudRepository<CustomClientDetails, String> {

    @Transactional(
            readOnly = true)
    public CustomClientDetails findByClientId(String clientId);

    @Transactional
    @Query(
            value = "update CustomClientDetails as c set c.clientId = ?1, c.clientSecret = ?2")
    public void updateClientIdAndClientSecret(String clientId, String clientSecret);
}
