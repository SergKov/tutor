package com.getprepared.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by koval on 29.01.2017.
 */
public class UserSignedInFilter implements Filter {

    private String roleAttribute;
    private String userHomePage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleAttribute = filterConfig.getInitParameter("role");
        userHomePage = filterConfig.getInitParameter("homePage");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest)request).getSession(false);
        if ((httpSession != null) && (httpSession.getAttribute(roleAttribute) != null)) {
            ((HttpServletResponse)response).sendRedirect(userHomePage);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}
