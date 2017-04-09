package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.constant.ServerConstant.ID;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 06.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    @Mock
    private ResultDao resultDao;

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private final ResultService resultService = new ResultServiceImpl();

    @Test
    public void requireInvokeSave() throws Exception {
        final Result result = new Result();
        resultService.save(result);
        verify(resultDao).save(result);
        verifyNoMoreInteractions(resultDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveWithException() throws Exception {
        final Result result = new Result();
        doThrow(new EntityExistsException()).when(resultDao).save(result);
        resultService.save(result);
    }

    @Test
    public void requireInvokeFindByUserId() throws Exception {
        final User user = new User();
        user.setId(ID);
        final PageableData pageableData = new PageableData();
        resultService.findByUserId(ID, pageableData);
        verify(resultDao).findByUserId(ID, pageableData);
        verify(resultDao).countFoundRows();
//        verify(userService).findById(ID);
//        verify(quizService).findById(ID);
    }
}