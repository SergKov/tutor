package com.getprepared.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.LINK;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 11.04.2017.
 */
public class StudentInTestFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
        if (httpSession.getAttribute(SESSION_ATTRIBUTE.TEST) != null) {
            ((HttpServletResponse) response).sendRedirect(LINK.STUDENT_TEST);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}
