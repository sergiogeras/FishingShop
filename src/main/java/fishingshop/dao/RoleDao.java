package fishingshop.dao;

import fishingshop.domain.user.Role;

import java.util.List;

/**
 * Created by sergei on 28.11.15.
 */
public interface RoleDao {
    List<Role> getRole(String role);
}
