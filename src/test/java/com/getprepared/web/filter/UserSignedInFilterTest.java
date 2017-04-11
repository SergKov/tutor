package com.getprepared.web.filter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.getprepared.web.constant.FilterConstant.HOME_PAGE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 31.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserSignedInFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterConfig config;

    @Mock
    private FilterChain chain;

    @InjectMocks
    private final Filter filter = new UserSignedInFilter();

    @Test
    public void requireInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, times(2)).getInitParameter(anyString());
        verifyNoMoreInteractions(config);
    }

    @Test
    public void requireInteractionsDoFilter() throws Exception {
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(chain);
    }

    @Test
    public void requireInteractionsDoFilterWhenSessionCreated() throws Exception {
        when(request.getSession(false)).thenReturn(notNull(HttpSession.class));
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
    }

    @Test
    public void requireNoInteractionsDoFilterWithNoSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(chain);
    }
}