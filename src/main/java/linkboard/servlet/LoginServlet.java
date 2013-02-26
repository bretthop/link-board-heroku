package linkboard.servlet;

import linkboard.data.entity.UserAccountEntity;
import linkboard.service.UserAccountService;
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
    urlPatterns = {"/loginServlet"}
)
public class LoginServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final UserAccountService userAccountService = new UserAccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String authToken = req.getHeader("Authorization");

        // TODO: Add base64 decoding
        String username = authToken.split("Basic ")[1].split(":")[0];
        String password = authToken.split("Basic ")[1].split(":")[1];

        UserAccountEntity user = userAccountService.getByUsernameAndPassword(username, password);

        if (user != null) {
            resp.setHeader("Content-Type", "application/json");

            ServletOutputStream out = resp.getOutputStream();
            out.write(JsonUtil.serialise(user).getBytes());
            out.flush();
            out.close();
        }
        else {
            resp.setStatus(400);
        }
    }
}