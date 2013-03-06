package linkboard.spring.ui.controller;

import linkboard.spring.TemporaryUser;
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
import java.util.List;

@Controller
@RequestMapping(value = "/linkGroups")
public class LinkGroupController
{
    @Resource
    LinkGroupService linkGroupService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<LinkGroupEntity> getCollection()
    {
        List<LinkGroupEntity> result;
        UserAccountEntity user = TemporaryUser.get();

        result = linkGroupService.getAllForUser(user);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody LinkGroupEntity postCollection(@Valid @RequestBody LinkGroupEntity linkGroup)
    {
        LinkGroupEntity result;
        UserAccountEntity user = TemporaryUser.get();

        linkGroup.setUser(user);

        result = linkGroupService.saveLinkGroup(linkGroup);

        return result;
    }
}
