package com.getprepared.core.service.impl;

import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

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
    private AnswerDao answerDao;

    @Mock
    private AnswerService answerService;

    @Ignore
    @Test
    public void save() throws Exception {
        answerService.save(answer);
        verify(answerDao).save(answer);
        verifyNoMoreInteractions(answerDao);
    }

    @Test
    public void save1() throws Exception {
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findByQuestionId() throws Exception {
    }

    @Test
    public void findByQuestionIdRandom() throws Exception {
    }

}