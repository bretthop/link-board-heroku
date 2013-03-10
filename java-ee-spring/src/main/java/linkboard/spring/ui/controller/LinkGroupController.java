package linkboard.spring.ui.controller;

import linkboard.spring.data.entity.LinkGroupEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.service.LinkGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/linkGroups")
public class LinkGroupController extends BaseController
{
    @Resource
    LinkGroupService linkGroupService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<LinkGroupEntity> getCollection(Principal principal)
    {
        List<LinkGroupEntity> result;
        UserAccountEntity user = this.getUserFromPrincipal(principal);

        result = linkGroupService.getAllForUser(user);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody LinkGroupEntity postCollection(@Valid @RequestBody LinkGroupEntity linkGroup, Principal principal)
    {
        LinkGroupEntity result;
        UserAccountEntity user = this.getUserFromPrincipal(principal);

        linkGroup.setUser(user);

        result = linkGroupService.saveLinkGroup(linkGroup);

        return result;
    }
}
