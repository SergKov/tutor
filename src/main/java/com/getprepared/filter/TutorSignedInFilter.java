package com.getprepared.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 29.01.2017.
 */
public class TutorSignedInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpSession httpSession = ((HttpServletRequest)request).getSession(false);
        if ((httpSession != null) && (httpSession.getAttribute(SESSION_ATTRIBUTES.TUTOR) != null)) {
            ((HttpServletResponse)response).sendRedirect(LINKS.TUTOR_QUESTIONS);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}
