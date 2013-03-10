package linkboard.spring.ui.controller;

import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.security.SecurityUserWrapper;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class BaseController
{
    protected UserAccountEntity getUserFromPrincipal(Principal principal)
    {
        UserAccountEntity result = null;

        if (principal instanceof Authentication) {
            Object userWrapper = ((Authentication) principal).getPrincipal();

            if (userWrapper instanceof SecurityUserWrapper) {
                result = ((SecurityUserWrapper) userWrapper).getLinkBoardUser();
            }
        }

        return result;
    }
}
