package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.dao.QuizDao;
import com.getprepared.persistence.domain.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by koval on 11.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {

    @Mock
    private QuizDao quizDao;

    @Mock
    private UserService userService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuizService quizService = new QuizServiceImpl();

    @Test
    public void requireInvokeSave() throws Exception {
        final Quiz result = new Quiz();
        quizService.save(result);
        verify(quizDao).save(result);
        verifyNoMoreInteractions(quizDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveWithException() throws Exception {
        final Quiz quiz = new Quiz();
        doThrow(new EntityExistsException()).when(quizDao).save(quiz);
        quizService.save(quiz);
    }

    @Test
    public void requireInvokeRemove() throws Exception {
        quizService.remove(anyLong());
        verify(quizDao).remove(anyLong());
        verifyNoMoreInteractions(quizDao);
    }
}