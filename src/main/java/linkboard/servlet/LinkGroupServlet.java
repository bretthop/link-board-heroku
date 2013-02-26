package linkboard.servlet;

import linkboard.data.entity.LinkGroupEntity;
import linkboard.service.LinkGroupService;
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
    private static final LinkGroupService linkGroupService = new LinkGroupService();
    private static final LinkValidator linkValidator = new LinkValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<LinkGroupEntity> linkGroups = linkGroupService.getAll();

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