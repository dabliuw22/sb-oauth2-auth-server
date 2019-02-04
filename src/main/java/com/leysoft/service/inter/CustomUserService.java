
package com.leysoft.service.inter;

import com.leysoft.entity.CustomUser;

public interface CustomUserService {

    public boolean save(CustomUser user);

    public CustomUser findById(Long id);

    public CustomUser findByUsername(String username);

    public boolean update(CustomUser user);

    public boolean delete(Long id);
}
