package com.getprepared.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 20.01.2017.
 */
public class StudentGuestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {  }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest)request).getSession(false);
        if (httpSession == null || httpSession.getAttribute(SESSION_ATTRIBUTES.STUDENT) == null) {
            ((HttpServletResponse)response).sendRedirect(LINKS.STUDENT_SIGN_IN);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {  }
}
