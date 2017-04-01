package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.annotation.AnswerType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by koval on 01.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerTypeConstraintValidatorTest {

    @Mock
    private AnswerType answerType;

    @InjectMocks
    private AnswerTypeConstraintValidator answerTypeConstraintValidator;

    @Test
    public void requireExceptionWithEmptyArray() throws Exception {
        Mockito.when(answerType.value()).thenReturn(Type.values());
        Assert.assertFalse(answerTypeConstraintValidator.isValid(new String[2]));
    }

    @Test(expected = NullPointerException.class)
    public void requireExceptionWithNull() throws Exception {
        Mockito.when(answerType.value()).thenReturn(Type.values());
        Assert.assertFalse(answerTypeConstraintValidator.isValid(null));
    }

}