
package com.leysoft.service.inter;

import com.leysoft.entity.CustomUser;

public interface CustomUserService {

    public boolean save(CustomUser user);

    public CustomUser getUser();

    public CustomUser getUserById(Long id);

    public CustomUser getUserByUsername(String username);

    public boolean update(CustomUser user);

    public boolean delete(Long id);
}
