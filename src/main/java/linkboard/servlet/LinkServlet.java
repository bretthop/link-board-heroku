package linkboard.servlet;

import linkboard.data.entity.LinkEntity;
import linkboard.data.entity.UserAccountEntity;
import linkboard.service.LinkService;
import linkboard.service.UserAccountService;
import linkboard.util.JsonUtil;
import linkboard.util.NumberUtil;
import linkboard.util.RequestUtil;
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
    urlPatterns = {"/api/links"}
)
public class LinkServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final UserAccountService userAccountService = new UserAccountService();
    private static final LinkService linkService = new LinkService();
    private static final LinkValidator linkValidator = new LinkValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
        UserAccountEntity user = (UserAccountEntity) req.getAttribute("currentUser");
        Long groupId = NumberUtil.tryParseLong(req.getParameter("groupId"));

        if (userAccountService.hasAccessToGroup(user, groupId)) {
            List<LinkEntity> links = linkService.getAllForGroup(groupId);

            resp.setHeader("Content-Type", "application/json");

            ServletOutputStream out = resp.getOutputStream();
            out.write(JsonUtil.serialise(links).getBytes());
            out.flush();
            out.close();
        }
        else {
            resp.setStatus(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserAccountEntity user = (UserAccountEntity) req.getAttribute("currentUser");

        String reqBody = RequestUtil.getRequestBody(req);
        LinkEntity link = JsonUtil.deserialise(reqBody, LinkEntity.class);

        if (userAccountService.hasAccessToGroup(user, link.getGroup())) {
            linkValidator.validateLink(link);

            link = linkService.saveLink(link);

            resp.setHeader("Content-Type", "application/json");

            ServletOutputStream out = resp.getOutputStream();
            out.write(JsonUtil.serialise(link).getBytes());
            out.flush();
            out.close();
        }
        else {
            resp.setStatus(403);
        }
    }
}