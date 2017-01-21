package com.getprepared.filter;

import com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;

/**
 * Created by koval on 21.01.2017.
 */
public class ChangeStudentToTutorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession(false);
        if (httpSession != null && httpSession.getAttribute(SESSION_ATTRIBUTES.STUDENT) != null) {
            ((HttpServletResponse)servletResponse).sendRedirect(LINKS.NOT_FOUND);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() { }
}
