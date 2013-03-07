package linkboard.spring.security;

import linkboard.spring.TemporaryUser;
import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class LoginHandler implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        UserAccountEntity user = TemporaryUser.get();

        User springUser = new User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                this.getAuthorities(user));

        return springUser;
    }

    private List<GrantedAuthority> getAuthorities(UserAccountEntity user)
    {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        authList.add(new GrantedAuthorityImpl("ROLE_USER"));

        return authList;
    }
}
