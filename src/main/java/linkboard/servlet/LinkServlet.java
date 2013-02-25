package linkboard.servlet;

import linkboard.data.entity.LinkEntity;
import linkboard.data.entity.LinkGroupEntity;
import linkboard.service.LinkService;
import linkboard.util.JsonUtil;
import linkboard.util.NumberUtil;
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
    name = "LinkServlet",
    urlPatterns = {"/linkServlet"}
)
public class LinkServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final LinkService linkService = new LinkService();
    private static final LinkValidator linkValidator = new LinkValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
        List<LinkEntity> links = linkService.getAll();

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(links).getBytes());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // TODO: Use Jackson to de-serialise the request body into LinkEntity
        Long groupId       = NumberUtil.tryParseLong(req.getParameter("groupId"));
        String title       = req.getParameter("title");
        String href        = req.getParameter("href");
        String description = req.getParameter("description");

        LinkEntity link = new LinkEntity();

        LinkGroupEntity group = new LinkGroupEntity();
        group.setId(groupId);
        link.setGroup(group);

        link.setTitle(title);
        link.setHref(href);
        link.setDescription(description);

        linkValidator.validateLink(link);

        link = linkService.saveLink(link);

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(link).getBytes());
        out.flush();
        out.close();
    }
}