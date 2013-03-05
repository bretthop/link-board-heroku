package linkboard.filter;

import linkboard.data.entity.UserAccountEntity;
import linkboard.exception.RestException;
import linkboard.service.UserAccountService;
import linkboard.util.Base64Util;

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
                String decodedToken = Base64Util.decode(authToken.split("Basic ")[1]);
                String username = decodedToken.split(":")[0];
                String password = decodedToken.split(":")[1];

                UserAccountEntity user = userAccountService.getByUsernameAndPassword(username, password);

                if (user != null) {
                    httpRequest.setAttribute("currentUser", user);
                }
                else {
                    throw new RestException(401);
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    { }
}
