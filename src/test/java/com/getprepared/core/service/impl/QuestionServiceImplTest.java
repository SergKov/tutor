package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.dao.QuestionDao;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.getprepared.constant.ServerConstant.ID;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 05.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private Question question;

    @Mock
    private List<Answer> answers;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuestionService questionService = new QuestionServiceImpl();

    @Ignore
    @Test // TODO
    public void requireInvokeSaveQuestion() throws Exception {
//        questionService.save(question);
//        verify(questionDao).save(question);
//        verify(question).getAnswers();
//        verifyNoMoreInteractions(questionDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveQuestionWithException() throws Exception {
        doThrow(new EntityExistsException()).when(questionDao).save(question);
        questionService.save(question);
    }

    @Test // TODO
    public void requireInvokeFindById() throws Exception {
//        when(question.getId()).thenReturn(ID);
//        doReturn(answerService).when(questionService).getAnswerService();
//        questionService.findById(question.getId());
//        verify(questionDao).findById(question.getId());
//        verifyNoMoreInteractions(questionDao);
    }
}