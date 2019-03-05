
package com.leysoft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.leysoft.entity.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    @Transactional(
            readOnly = true)
    @Query(
            value = "select u from CustomUser as u inner join fetch u.roles "
                    + "left join fetch u.clients where u.username = ?1")
    public Optional<CustomUser> findByUsername(String username);

    @Override
    @Transactional(
            readOnly = true)
    @Query(
            value = "select u from CustomUser as u inner join fetch u.roles "
                    + "left join fetch u.clients where u.id = ?1")
    public Optional<CustomUser> findById(Long id);

    @Override
    @Transactional(
            readOnly = true)
    @Query(
            value = "select u from CustomUser as u inner join fetch u.roles "
                    + "left join fetch u.clients")
    public List<CustomUser> findAll();
}
