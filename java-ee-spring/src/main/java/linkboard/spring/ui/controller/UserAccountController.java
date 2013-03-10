package linkboard.spring.ui.controller;

import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserAccountController extends BaseController
{
    @Resource
    UserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserAccountEntity getCollection(Principal principal)
    {
        return this.getUserFromPrincipal(principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody UserAccountEntity postCollection(@Valid @RequestBody UserAccountEntity user)
    {
        UserAccountEntity result;

        result = userAccountService.createUser(user);

        return result;
    }
}