package linkboard.servlet;

import linkboard.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    name = "LoginServlet",
    urlPatterns = {"/api/login"}
)
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(req.getAttribute("currentUser")).getBytes());
        out.flush();
        out.close();
    }
}