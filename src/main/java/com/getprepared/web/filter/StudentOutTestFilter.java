package com.getprepared.web.filter;

import com.getprepared.web.constant.ApplicationConstant;
import com.getprepared.web.constant.WebConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.WebConstant.*;
import static javax.servlet.http.HttpServletResponse.*;

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
