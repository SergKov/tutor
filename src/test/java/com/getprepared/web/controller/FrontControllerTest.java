package com.getprepared.web.controller;

import com.getprepared.context.Registry;
import com.getprepared.context.WebContext;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.context.Registry.*;
import static javax.servlet.http.HttpServletResponse.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 01.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private WebContext webContext;

    @InjectMocks
    private FrontController frontController = new FrontController();

    @Test
    @Ignore // TODO
    public void doGetWithRequestUriNull() throws Exception {
//        when(request.getRequestURI()).thenReturn(null);
//        when(getWebContext()).thenReturn(webContext);
//        doReturn(null).when(getWebContext().getCommand(null));
//        verify(response).sendError(SC_NOT_FOUND);
//        verifyNoMoreInteractions(request);
//        verifyNoMoreInteractions(response);
    }

    @Test
    @Ignore
    public void doPost() throws Exception {
    }

}