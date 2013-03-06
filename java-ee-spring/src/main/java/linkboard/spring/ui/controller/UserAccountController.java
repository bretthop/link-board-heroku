package linkboard.spring.ui.controller;

import linkboard.spring.TemporaryUser;
import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserAccountController
{
    @Resource
    UserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserAccountEntity getCollection()
    {
        UserAccountEntity user = TemporaryUser.get();

        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody UserAccountEntity postCollection(@Valid @RequestBody UserAccountEntity user, BindingResult validationErrors)
    {
        UserAccountEntity result;

        result = userAccountService.createUser(user);

        return result;
    }
}
