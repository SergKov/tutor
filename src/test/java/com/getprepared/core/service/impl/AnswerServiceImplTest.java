package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.getprepared.constant.ServerConstant.ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 04.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceImplTest {

    @Mock
    private Answer answer;

    @Mock
    private List<Answer> answers;

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
    }

    @Test
    public void requireInvokeFindById() throws Exception {
        when(answer.getId()).thenReturn(ID);
        answerService.findById(answer.getId());
        verify(answerDao).findById(answer.getId());
        verifyNoMoreInteractions(answerDao);
    }

    @Test(expected = EntityNotFoundException.class)
    public void requireFindByIdWithException() throws Exception {
        doThrow(new EntityNotFoundException()).when(answerDao).findById(ID);
        answerService.findById(ID);
    }

    @Test
    public void requireFindByIdWithSomeDaoResult() throws Exception { // TODO rename
        doReturn(answer).when(answerDao).findById(ID);
        final Answer originalAnswer = answerService.findById(ID);
        assertEquals(answer, originalAnswer);
    }

    @Test
    public void requireInvokeFindByQuestionId() throws Exception {
        when(question.getId()).thenReturn(ID);
        answerService.findByQuestionId(question.getId());
        verify(answerDao).findByQuestionId(question.getId());
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void requireFindByQuestionIdWithSomeDaoResult() throws Exception {
        doReturn(answers).when(answerDao).findByQuestionId(ID);
        final List<Answer> originalAnswers = answerService.findByQuestionId(ID);
        assertEquals(originalAnswers, answers);
    }

    @Test
    public void requireInvokeFindRandomAnswersByQuestionId() throws Exception {
        when(question.getId()).thenReturn(ID);
        answerService.findByQuestionIdInRandomOrder(question.getId());
        verify(answerDao).findByQuestionIdInRandomOrder(question.getId());
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void requireFindRandomAnswersWithSomeDaoResult() throws Exception {
        doReturn(answers).when(answerDao).findByQuestionIdInRandomOrder(ID);
        final List<Answer> originalAnswers = answerService.findByQuestionIdInRandomOrder(ID);
        assertEquals(originalAnswers, answers);
    }
}