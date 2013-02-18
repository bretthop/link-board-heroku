package linkboard.servlet;

import linkboard.data.entity.LinkEntity;
import linkboard.service.LinkService;
import linkboard.util.JsonUtil;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
        ServletOutputStream out = resp.getOutputStream();

        List<LinkEntity> links = linkService.getAll();

        resp.setHeader("Content-Type", "application/json");

        out.write(JsonUtil.serialise(links).getBytes());
        out.flush();
        out.close();
    }
}