package com.getprepared.core.service.impl;

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
    private User user;

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void requireInvokeFindById() throws Exception {
        userService.findById(ID);
        verify(userDao).findById(ID);
        verifyNoMoreInteractions(userDao);
        verifyZeroInteractions(user);
    }

    @Ignore
    @Test
    public void requireInvokeSignInStudent() throws Exception {
        userService.signInStudent(EMAIL, PASSWORD);
        verify(userDao).findByTutorEmail(EMAIL);
        verifyNoMoreInteractions(userDao);
        verifyZeroInteractions(user);
    }
}