package com.getprepared.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Created by koval on 29.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserGuestFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterConfig config;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpSession session;

    private Filter filter;

    @Before
    public void setUp() {
        filter = new UserGuestFilter();
    }

    @Test
    public void requireInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, times(2)).getInitParameter(anyString());
        verifyNoMoreInteractions(config);
    }

    @Test
    public void requireNoInteractionsDoFilterWhenNoSession() throws Exception {
        filter.doFilter(request, response, chain);
        verify(chain, never()).doFilter(request, response);
    }
}