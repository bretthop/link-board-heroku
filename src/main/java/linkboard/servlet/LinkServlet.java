package linkboard.servlet;

import linkboard.data.entity.LinkEntity;
import linkboard.service.LinkService;

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
        resp.setHeader("Content-Type", "application/json");
        ServletOutputStream out = resp.getOutputStream();

        List<LinkEntity> links = linkService.getAll();

        // TODO: Replace with JSON (de)serialiser
        String linkJSON = "{\"id\":%s,\"title\":\"%s\",\"href\":\"%s\",\"description\":\"%s\"}";
        out.write("[".getBytes());
        for (int i = 0; i < links.size(); i++) {
            LinkEntity link = links.get(i);

            out.write(String.format(linkJSON, link.getId(), link.getTitle(), link.getHref(), link.getDescription()).getBytes());

            if (i < (links.size() - 1)) {
                out.write(",".getBytes());
            }
        }
        out.write("]".getBytes());

        out.flush();
        out.close();
    }
}