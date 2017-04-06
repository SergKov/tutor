package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.ResultService;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.domain.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by koval on 06.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    @Mock
    private Result result;

    @Mock
    private ResultDao resultDao;

    @InjectMocks
    private final ResultService resultService = new ResultServiceImpl();

    @Test
    public void requireInvokeSave() throws Exception {
        resultService.save(result);
        verify(resultDao).save(result);
        verifyNoMoreInteractions(result);
        verifyNoMoreInteractions(resultDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveWithException() throws Exception {
        doThrow(new EntityExistsException()).when(resultDao).save(result);
        resultService.save(result);
    }
}