package linkboard.spring.ui.controller;

import linkboard.spring.data.entity.LinkEntity;
import linkboard.spring.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/links")
public class LinkController
{
    @Resource
    LinkService linkService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLinks(ModelMap model)
    {
        List<LinkEntity> links = linkService.getAll();

        model.addAttribute("links", links);

        return "links";
    }
}
