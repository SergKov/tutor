package com.getprepared.core.service.impl;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.ServerConstant.ID;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 05.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void requireInvokeSaveQuestion() throws Exception {
        final Question question = new Question();
        question.setAnswers(new ArrayList<>());
        questionService.save(question);
        verify(questionDao).save(question);
        verify(answerService).save(question.getAnswers());
        verifyNoMoreInteractions(questionDao);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveQuestionWithQuestionDaoException() throws Exception {
        final Question question = new Question();
        doThrow(new EntityExistsException()).when(questionDao).save(question);
        questionService.save(question);
    }

    @Test(expected = EntityExistsException.class)
    public void requireSaveQuestionWithAnswerServiceException() throws Exception {
        final Question question = new Question();
        final List<Answer> answers = new ArrayList<>();
        question.setAnswers(answers);
        doThrow(new EntityExistsException()).when(answerService).save(answers);
        questionService.save(question);
        verify(questionDao).save(question);
    }
}