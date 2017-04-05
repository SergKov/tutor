package com.getprepared.core.service.impl;

import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.dao.QuestionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by koval on 05.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private AnswerService answerService;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void requireSaveQuestion() {
//        Mockito.when(questionService)
    }



}