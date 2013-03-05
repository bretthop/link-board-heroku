package linkboard.servlet;

import linkboard.data.entity.LinkGroupEntity;
import linkboard.data.entity.UserAccountEntity;
import linkboard.service.LinkGroupService;
import linkboard.util.JsonUtil;
import linkboard.util.RequestUtil;
import linkboard.validator.LinkValidator;
import linkboard.validator.UserAccountValidator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
    name = "LinkGroupServlet",
    urlPatterns = {"/api/linkGroups"}
)
public class LinkGroupServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final LinkGroupService linkGroupService = new LinkGroupService();
    private static final LinkValidator linkValidator = new LinkValidator();
    private static final UserAccountValidator userAccountValidator = new UserAccountValidator();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserAccountEntity user = (UserAccountEntity) req.getAttribute("currentUser");
        userAccountValidator.validateUser(user);

        List<LinkGroupEntity> linkGroups = linkGroupService.getAllForUser(user);

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(linkGroups).getBytes());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserAccountEntity user = (UserAccountEntity) req.getAttribute("currentUser");
        userAccountValidator.validateUser(user);

        String reqBody = RequestUtil.getRequestBody(req);
        LinkGroupEntity linkGroup = JsonUtil.deserialise(reqBody, LinkGroupEntity.class);
        linkGroup.setUser(user);

        linkValidator.validateLinkGroup(linkGroup);

        linkGroup = linkGroupService.saveLinkGroup(linkGroup);

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(linkGroup).getBytes());
        out.flush();
        out.close();
    }
}