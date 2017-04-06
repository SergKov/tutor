package com.getprepared.core.service.impl;

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

import static com.getprepared.constant.ServerConstant.EMAIL;
import static com.getprepared.constant.ServerConstant.ID;
import static com.getprepared.constant.ServerConstant.PASSWORD;
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
    public void requireFailedSignInStudent() throws Exception {
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
    public void requireFailedSignInTutor() throws Exception {
        when(userDao.findByTutorEmail(anyString())).thenReturn(new User());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        userService.signInTutor(EMAIL, PASSWORD);
    }
}