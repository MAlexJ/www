package filters;

import constants.Constants;
import entity.Account;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 12.05.15.
 */

public class WebtaskLoginFilter extends AbstractWebtasksFilter implements Constants {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Account currentSession = (Account) request.getSession().getAttribute(CURRENT_SESSION_ACCOUNT);

        String uri = request.getRequestURI();
        String context = request.getContextPath();

        if (request.getRequestURI().endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.endsWith("/logout.php")
                || uri.endsWith("/error.php") || uri.endsWith("/login.php")
                || uri.endsWith("/pageNotFound.php")) {
            chain.doFilter(request, response);
        } else if (currentSession != null) {
            String currentRole = request.getSession()
                    .getAttribute(CURRENT_ROLE).toString();
            if (currentRole.equals("1") && (uri.indexOf("/student/") == -1)) {
                chain.doFilter(request, response);
            } else if (currentRole.equals("2")
                    && (uri.indexOf("/admin/") == -1)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(context + "/error.php?notfound=true");
            }
        } else {
            response.sendRedirect(context + "/login.php");
        }
    }
}
