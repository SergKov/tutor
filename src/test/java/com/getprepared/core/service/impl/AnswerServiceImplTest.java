package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.database.TransactionManager;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 04.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceImplTest {

    @Mock
    private Answer answer;

    @Mock
    private Question question;

    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private final AnswerService answerService = new AnswerServiceImpl();

    @Test
    public void requireInvokeSave() throws Exception {
        answerService.save(answer);
        verify(answerDao).save(answer);
        verifyNoMoreInteractions(answer);
        verifyNoMoreInteractions(answerDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveWithException() throws Exception {
        doThrow(new EntityExistsException()).when(answerDao).save(answer);
        answerService.save(answer);
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void requireInvokeFindById() throws Exception {
        when(answer.getId()).thenReturn(-58L);
        answerService.findById(answer.getId());
        verify(answerDao).findById(answer.getId());
        verifyNoMoreInteractions(answerDao);
    }

    @Test(expected = EntityNotFoundException.class)
    public void requireFindByIdWithException() throws Exception {
        doThrow(new EntityNotFoundException()).when(answerDao).findById(-5L);
        answerService.findById(-5L);
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void requireInvokeFindByQuestionId() throws Exception {
        when(question.getId()).thenReturn(100L);
        answerService.findByQuestionId(question.getId());
        verify(answerDao).findByQuestionId(question.getId());
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void requireInvokeFindByQuestionIdRandom() throws Exception {
        when(question.getId()).thenReturn(12L);
        answerService.findByQuestionIdRandom(question.getId());
        verify(answerDao).findByQuestionIdRandom(question.getId());
        verifyNoMoreInteractions(answerDao);
    }
}