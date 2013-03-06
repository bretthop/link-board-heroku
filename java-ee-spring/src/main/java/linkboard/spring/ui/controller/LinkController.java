package linkboard.spring.ui.controller;

import linkboard.spring.RestException;
import linkboard.spring.TemporaryUser;
import linkboard.spring.data.entity.LinkEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import linkboard.spring.service.LinkService;
import linkboard.spring.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/links")
public class LinkController
{
    @Resource
    LinkService linkService;

    @Resource
    UserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<LinkEntity> getCollection(@RequestParam("groupId") long groupId)
    {
        List<LinkEntity> result = new ArrayList<LinkEntity>();

        // Dummy user for now
        UserAccountEntity user = TemporaryUser.get();

        if (userAccountService.hasAccessToGroup(user, groupId)) {
            result = linkService.getAllForGroup(groupId);
        }
        else {
            throw new RestException(403);
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody LinkEntity postCollection(@Valid @RequestBody LinkEntity link)
    {
        LinkEntity result;
        UserAccountEntity user = TemporaryUser.get();

        if (userAccountService.hasAccessToGroup(user, link.getGroup())) {
            result = linkService.saveLink(link);
        }
        else {
            throw new RestException(403);
        }

        return result;
    }
}
