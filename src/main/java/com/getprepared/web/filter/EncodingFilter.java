package com.getprepared.web.filter;

import javax.servlet.*;
import java.io.IOException;

import static com.getprepared.web.constant.FilterConstant.ENCODING;


/**
 * Created by koval on 20.01.2017.
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}

