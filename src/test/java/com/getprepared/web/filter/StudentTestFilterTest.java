package com.getprepared.web.filter;

import com.getprepared.web.constant.WebConstant;
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
import javax.servlet.http.HttpSession;

import static com.getprepared.web.constant.WebConstant.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

/**
 * Created by koval on 11.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentTestFilterTest {

    @Mock
    private FilterConfig config;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private FilterChain chain;

    @InjectMocks
    private final Filter filter = new StudentTestFilter();

    @Test
    public void requireNoInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, never()).getInitParameter(anyString());
        verifyNoMoreInteractions(config, request, response, chain);
    }

    @Test
    public void requireInteractionsDoFilterWithNoSession() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(SESSION_ATTRIBUTE.TEST)).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(chain, response, response);
    }

    @Test
    public void requireNoInteractionsWithSession() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(SESSION_ATTRIBUTE.TEST)).thenReturn(anyString());
        filter.doFilter(request, response, chain);
        verify(response, only()).sendRedirect(anyString());
        verifyNoMoreInteractions(chain, response);
    }

}