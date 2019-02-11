
package com.leysoft.service.imple;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.leysoft.entity.CustomUser;
import com.leysoft.repository.CustomUserRepository;
import com.leysoft.service.inter.CustomUserService;
import com.leysoft.util.SecurityUtils;

@Service
public class CustomUserServiceImp implements CustomUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserServiceImp.class);

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public boolean save(CustomUser user) {
        boolean saved;
        try {
            customUserRepository.save(user);
            saved = true;
        } catch (Exception e) {
            saved = false;
            LOGGER.error("The user: {} could not be saved... ", user, e);
        }
        return saved;
    }

    @Nullable
    @Override
    public CustomUser getUser() {
        CustomUser user = null;
        try {
            String currentUsername = SecurityUtils.getCurrentUsername();
            if (Objects.nonNull(currentUsername)) {
                user = getUserByUsername(currentUsername);
            }
        } catch (Exception e) {
            LOGGER.error("The current user was not found... ", e);
        }
        return user;
    }

    @Nullable
    @Override
    public CustomUser getUserById(Long id) {
        CustomUser user = null;
        try {
            user = customUserRepository.findById(id).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Not found user by id: {}... ", id, e);
        }
        return user;
    }

    @Nullable
    @Override
    public CustomUser getUserByUsername(String username) {
        CustomUser user = null;
        try {
            user = customUserRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Not found user by username: {}... ", username, e);
        }
        return user;
    }

    @Override
    public boolean update(CustomUser user) {
        boolean updated;
        try {
            customUserRepository.save(user);
            updated = true;
        } catch (Exception e) {
            updated = false;
            LOGGER.error("The user: {} could not be updated... ", user, e);
        }
        return updated;
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        try {
            CustomUser user = getUserById(id);
            if (Objects.nonNull(user)) {
                customUserRepository.deleteById(id);
                deleted = true;
            }
        } catch (Exception e) {
            LOGGER.error("The user id: {} could not be deleted... ", id, e);
        }
        return deleted;
    }
}
