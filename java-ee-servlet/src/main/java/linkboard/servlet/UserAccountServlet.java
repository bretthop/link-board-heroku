package linkboard.servlet;

import linkboard.data.entity.UserAccountEntity;
import linkboard.service.UserAccountService;
import linkboard.util.JsonUtil;
import linkboard.util.RequestUtil;
import linkboard.validator.UserAccountValidator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    name = "UserAccountServlet",
    urlPatterns = {"/api/users"}
)
public class UserAccountServlet extends HttpServlet
{
    //TODO: Use CDI
    private static final UserAccountService userAccountService = new UserAccountService();
    private static final UserAccountValidator userAccountValidator = new UserAccountValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserAccountEntity user = (UserAccountEntity) req.getAttribute("currentUser");
        userAccountValidator.validateUser(user);

        resp.setHeader("Content-Type", "application/json");

        ServletOutputStream out = resp.getOutputStream();
        out.write(JsonUtil.serialise(user).getBytes());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String reqBody = RequestUtil.getRequestBody(req);
        UserAccountEntity newUser = JsonUtil.deserialise(reqBody, UserAccountEntity.class);

        userAccountValidator.validateUser(newUser);
        userAccountService.createUser(newUser);
    }
}