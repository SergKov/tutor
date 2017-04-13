package com.getprepared.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 11.04.2017.
 */
public class StudentOutTestFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
        if (httpSession == null || httpSession.getAttribute(SESSION_ATTRIBUTE.TEST) == null) {
            ((HttpServletResponse)response).sendError(SC_NOT_FOUND);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}
