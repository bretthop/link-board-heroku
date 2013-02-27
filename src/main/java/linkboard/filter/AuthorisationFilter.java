package linkboard.filter;

import linkboard.data.entity.UserAccountEntity;
import linkboard.service.UserAccountService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorisationFilter implements Filter
{
    private static final UserAccountService userAccountService = new UserAccountService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String authToken = httpRequest.getHeader("Authorization");

            if (authToken != null) {
                // TODO: Add base64 decoding
                String username = authToken.split("Basic ")[1].split(":")[0];
                String password = authToken.split("Basic ")[1].split(":")[1];

                UserAccountEntity user = userAccountService.getByUsernameAndPassword(username, password);

                if (user != null) {
                    httpRequest.setAttribute("currentUser", user);
                }
                else {
                    ((HttpServletResponse) response).setStatus(401);

                    return;
                }
            }
            else {
                ((HttpServletResponse) response).setStatus(400);

                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    { }
}
