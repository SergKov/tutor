package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.PasswordEncoder;
import com.getprepared.persistence.dao.UserDao;
import com.getprepared.persistence.domain.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static com.getprepared.constant.ServerConstant.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 06.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void requireInvokeFindById() throws Exception {
        userService.findById(anyLong());
        verify(userDao).findById(anyLong());
        verifyNoMoreInteractions(userDao);
    }

    @Test
    public void requireInvokeSignInStudent() throws Exception {
        when(userDao.findByStudentEmail(anyString())).thenReturn(new User());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        userService.signInStudent(EMAIL, PASSWORD);
        verify(userDao).findByStudentEmail(anyString());
        verifyNoMoreInteractions(userDao);
    }

    @Test(expected = EntityNotFoundException.class)
    public void requireFailSignInStudent() throws Exception {
        when(userDao.findByStudentEmail(anyString())).thenReturn(new User());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        userService.signInStudent(EMAIL, PASSWORD);
    }

    @Test
    public void requireInvokeSignInTutor() throws Exception {
        when(userDao.findByTutorEmail(anyString())).thenReturn(new User());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        userService.signInTutor(EMAIL, PASSWORD);
        verify(userDao).findByTutorEmail(anyString());
        verifyNoMoreInteractions(userDao);
    }

    @Test(expected = EntityNotFoundException.class)
    public void requireFailSignInTutor() throws Exception {
        when(userDao.findByTutorEmail(anyString())).thenReturn(new User());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        userService.signInTutor(EMAIL, PASSWORD);
    }

    @Test
    public void requireInvokeSignUp() throws Exception {
        final User user = new User();
        userService.signUp(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(PASSWORD);
        verify(userDao).save(user);
        verifyNoMoreInteractions(userDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireFailSignUp() throws Exception {
        final User user = new User();
        doThrow(EntityExistsException.class).when(userDao).save(user);
        userService.signUp(user);
    }

    @Test
    @Ignore // TODO
    public void requireInvokeUpdateStudentPassword() throws Exception {
        final User user = new User();
        user.setPassword(PASSWORD);
        when(userDao.findByStudentEmail(anyString())).thenReturn(user);
        userService.updateStudentPassword(EMAIL, PASSWORD, PASSWORD);
        verify(userDao).updateStudentPassword(anyString());
        verifyNoMoreInteractions(userDao);
    }

    @Ignore // TODO
    @Test(expected = EntityExistsException.class)
    public void requireFailUpdateStudentPassword() throws Exception {
        doThrow(EntityExistsException.class).when(userDao).updateStudentPassword(anyString());
        userService.updateStudentPassword(anyString(), anyString(), anyString());
    }

    @Ignore // TODO
    @Test
    public void requireInvokeUpdateTutorPassword() throws Exception {
        when(userDao.findByTutorEmail(any(String.class))).thenReturn(new User());
        userService.updateTutorPassword(anyString(), anyString(), anyString());
        verify(userDao).updateTutorPassword(any(String.class));
        verifyNoMoreInteractions(userDao);
    }

    @Ignore // TODO
    @Test(expected = EntityExistsException.class)
    public void requireFailUpdateTutorPassword() throws Exception {
        final User user = new User();
        user.setPassword(PASSWORD);
        when(userDao.findByTutorEmail(any(String.class))).thenReturn(user);
        doThrow(EntityExistsException.class).when(userDao).updateTutorPassword(user.getPassword());
        userService.updateTutorPassword(anyString(), PASSWORD, anyString());
    }
}