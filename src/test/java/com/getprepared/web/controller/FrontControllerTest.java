package com.getprepared.web.controller;

import com.getprepared.context.ApplicationContext;
import com.getprepared.context.Registry;
import com.getprepared.context.WebContext;
import com.getprepared.web.command.student.StudentSignInCommand;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.constant.ServerConstant.HOME_PAGE;
import static com.getprepared.constant.ServerConstant.ID;
import static com.getprepared.constant.ServerConstant.RANDOM_STRING;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 15.04.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Registry.class)
@SuppressStaticInitializationFor("com.getprepared.context.Registry")
public class FrontControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private StudentSignInCommand command;

    @Mock
    private WebContext webContext;

    @InjectMocks
    private FrontController frontController = new FrontController();

    @Test
    @Ignore // TODO
    public void requireDoGetWithForward() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(request.getRequestURI()).thenReturn(anyString());
        when(webContext.getCommand(anyString())).thenReturn(command);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(command.execute(request, response)).thenReturn(RANDOM_STRING);
        frontController.doPost(request, response);
        verify(command, only()).execute(request, response);
        verify(requestDispatcher, only()).forward(request, response);
    }

    @Test
    public void requireDoGetWithRedirect() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(webContext.getCommand(HOME_PAGE)).thenReturn(command);
        when(request.getRequestURI()).thenReturn(HOME_PAGE);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        frontController.doGet(request, response);
        verify(webContext.getCommand(HOME_PAGE)).execute(request, response);
        verify(command, only()).execute(request, response);
        verifyNoMoreInteractions(response, command);
    }

    @Test
    public void requireDoGetWithNotExistsCommand() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(request.getParameter(anyString())).thenReturn(HOME_PAGE);
        when(webContext.getCommand(anyString())).thenReturn(null);
        frontController.doGet(request, response);
        verify(response).sendError(anyInt());
        verifyNoMoreInteractions(response, command);
    }

    @Test
    @Ignore // TODO
    public void requireDoPostWithForward() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(request.getParameter(anyString())).thenReturn(HOME_PAGE);
        when(webContext.getCommand(HOME_PAGE)).thenReturn(command);
        when(request.getRequestDispatcher(HOME_PAGE)).thenReturn(requestDispatcher);
        when(command.execute(request, response)).thenReturn(RANDOM_STRING);
        frontController.doPost(request, response);
        verify(command, only()).execute(request, response);
        verify(requestDispatcher, only()).forward(request, response);
    }

    @Test
    public void requireDoPostWithRedirect() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(request.getRequestURI()).thenReturn(HOME_PAGE);
        when(webContext.getCommand(HOME_PAGE)).thenReturn(null);
        frontController.doPost(request, response);
        verify(webContext).getCommand(null);
        verify(response).sendError(anyInt());
        verifyNoMoreInteractions(response, command, webContext);
    }

    @Test
    public void requireDoPostWithNotExistsCommand() throws Exception {
        PowerMockito.mockStatic(Registry.class);
        PowerMockito.when(Registry.getWebContext()).thenReturn(webContext);
        when(request.getParameter(anyString())).thenReturn(HOME_PAGE);
        when(webContext.getCommand(anyString())).thenReturn(null);
        frontController.doPost(request, response);
        verify(response).sendError(anyInt());
        verifyNoMoreInteractions(response, command);
    }
}