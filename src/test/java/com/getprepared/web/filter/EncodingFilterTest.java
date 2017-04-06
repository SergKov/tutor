package com.getprepared.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.web.constant.FilterConstant.ENCODING;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 31.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterConfig config;

    @Mock
    private FilterChain chain;

    @InjectMocks
    private final Filter filter = new EncodingFilter();

    @Test
    public void requireNoInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, never()).getInitParameter(anyString());
        verifyNoMoreInteractions(config, request, response, chain);
    }

    @Test
    public void requireSetEncoding() throws Exception {
        filter.doFilter(request, response, chain);
        verify(request, only()).setCharacterEncoding(ENCODING);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(request, response, chain);
    }
}