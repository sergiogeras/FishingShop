package fishingshop.service.security;

import fishingshop.dao.UserDao;
import fishingshop.domain.user.Role;
import fishingshop.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Сергей on 21.11.2015.
 */

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.getUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);
    }


    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(), true, true, true, true, authorities);
    }


    private List<GrantedAuthority> buildUserAuthority(Role role) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(role.getRole()));
        return new ArrayList<>(setAuths);
    }

}
