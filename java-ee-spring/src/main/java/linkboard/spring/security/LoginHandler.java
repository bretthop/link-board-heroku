package linkboard.spring.security;

import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.service.UserAccountService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class LoginHandler implements UserDetailsService
{
    @Resource
    private UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        User springUser = null;

        UserAccountEntity linkBoardUser = userAccountService.getByUsername(username);

        if (linkBoardUser != null) {
            springUser = new User(
                linkBoardUser.getUsername(),
                linkBoardUser.getPassword(),
                true,
                true,
                true,
                true,
                this.getAuthorities(linkBoardUser));
        }

        return springUser;
    }

    private List<GrantedAuthority> getAuthorities(UserAccountEntity user)
    {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        authList.add(new GrantedAuthorityImpl("ROLE_USER"));

        return authList;
    }
}
