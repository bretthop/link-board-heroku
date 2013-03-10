package linkboard.spring.security;

import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class SecurityUserWrapper extends User
{
    private UserAccountEntity linkBoardUser;

    public SecurityUserWrapper(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserAccountEntity getLinkBoardUser()
    {
        return linkBoardUser;
    }

    public void setLinkBoardUser(UserAccountEntity linkBoardUser)
    {
        this.linkBoardUser = linkBoardUser;
    }

    public static SecurityUserWrapper fromLinkBoardUser(UserAccountEntity linkBoardUser, List<GrantedAuthority> authorities)
    {
        SecurityUserWrapper userWrapper = new SecurityUserWrapper(
                linkBoardUser.getUsername(),
                linkBoardUser.getPassword(),
                true,
                true,
                true,
                true,
                authorities);

        userWrapper.setLinkBoardUser(linkBoardUser);

        return userWrapper;
    }
}
