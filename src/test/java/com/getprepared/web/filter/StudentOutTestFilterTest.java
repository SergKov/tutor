package com.getprepared.web.filter;

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

import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 14.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentOutTestFilterTest {

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
    private final Filter filter = new StudentOutTestFilter();

    @Test
    public void requireNoInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, never()).getInitParameter(anyString());
        verifyNoMoreInteractions(config, request, response, chain);
    }

    @Test
    public void requireSendErrorWithNoSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(response, only()).sendError(anyInt());
        verifyNoMoreInteractions(chain, response, response);
    }

    @Test
    public void requireSendErrorWithNoSessionAttribute() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(SESSION_ATTRIBUTE.TEST)).thenReturn(null);
        filter.doFilter(request, response, chain);
        verify(response, only()).sendError(anyInt());
        verifyNoMoreInteractions(chain, response, response);
    }

    @Test
    public void requireNoInteractionsWithSession() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(SESSION_ATTRIBUTE.TEST)).thenReturn(anyString());
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(chain, response);
    }

}