package com.getprepared.web.filter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.getprepared.web.constant.FilterConstant.HOME_PAGE;
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

    @InjectMocks
    private final Filter filter = new UserGuestFilter();

    @Test
    public void requireInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, times(2)).getInitParameter(anyString());
        verifyNoMoreInteractions(config);
    }

//    @Test // TODO
//    public void requireNoInteractionsDoFilterWithSession() throws Exception {
//        when(request.getSession(false)).thenReturn(notNull(HttpSession.class));
//        filter.doFilter(request, response, chain);
//        verify(chain, only()).doFilter(request, response);
//        verifyNoMoreInteractions(chain, request, response);
//    }

//    @Test
//    public void requireNoInteractionsDoFilterWithNoSession() throws Exception {
//        when(request.getSession(false)).thenReturn(null);
//        filter.doFilter(request, response, chain);
//        verify(response, only()).sendRedirect(HOME_PAGE);
//        verifyNoMoreInteractions(chain, request, response);
//    }
}