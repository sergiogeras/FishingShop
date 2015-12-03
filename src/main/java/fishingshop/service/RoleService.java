package fishingshop.service;

import fishingshop.domain.user.Role;

import java.util.List;

/**
 * Created by sergei on 28.11.15.
 */
public interface RoleService {
    List<Role> getRole(String role);
}
