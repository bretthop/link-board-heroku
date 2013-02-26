package linkboard.servlet;

import linkboard.data.entity.LinkGroupEntity;
import linkboard.data.entity.UserAccountEntity;
import linkboard.service.LinkGroupService;
import linkboard.service.UserAccountService;
import linkboard.util.JsonUtil;
import linkboard.validator.LinkValidator;

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
    urlPatterns = {"/linkGroupServlet"}
)
public class LinkGroupServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final UserAccountService userAccountService = new UserAccountService();
    private static final LinkGroupService linkGroupService = new LinkGroupService();
    private static final LinkValidator linkValidator = new LinkValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String authToken = req.getHeader("Authorization");

        // TODO: Add base64 decoding
        String username = authToken.split("Basic ")[1].split(":")[0];
        String password = authToken.split("Basic ")[1].split(":")[1];

        UserAccountEntity user = userAccountService.getByUsernameAndPassword(username, password);

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
        // TODO: Use Jackson to de-serialise the request body into LinkEntity
        String title       = req.getParameter("title");
        String description = req.getParameter("description");

        LinkGroupEntity linkGroup = new LinkGroupEntity();

        linkGroup.setTitle(title);
        linkGroup.setDescription(description);

        linkValidator.validateLinkGroup(linkGroup);

        linkGroup = linkGroupService.saveLinkGroup(linkGroup);

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(linkGroup).getBytes());
        out.flush();
        out.close();
    }
}