package fishingshop.service.impl;

import fishingshop.dao.RoleDao;
import fishingshop.domain.user.Role;
import fishingshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sergei on 28.11.15.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getRole(String role) {
        return roleDao.getRole(role);
    }
}
