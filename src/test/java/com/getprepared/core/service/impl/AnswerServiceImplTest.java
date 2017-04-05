package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.database.TransactionManager;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Mock
    private TransactionManager transactionManager;

    @InjectMocks
    private final AnswerService answerService = new AnswerServiceImpl();

    @Test
    public void requireInvokeSave() throws Exception {
        answerService.save(answer);
        verify(answerDao).save(answer);
        verifyNoMoreInteractions(answer);
        verifyNoMoreInteractions(answerDao);
    }

    @Ignore
    @Test(expected = EntityExistsException.class)
    public void requireSaveException() throws Exception {
        when(answerDao.findById(answer.getId())).thenThrow(EntityExistsException.class);
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