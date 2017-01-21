package com.getprepared.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by koval on 20.01.2017.
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
